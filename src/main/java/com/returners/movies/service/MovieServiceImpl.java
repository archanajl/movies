package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFoundException;
import com.returners.movies.model.*;
import com.returners.movies.repository.CertificationRepository;
import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.repository.MovieRepository;
import com.returners.movies.repository.UserRepository;
import com.returners.movies.util.DynamicTemplatePersonalization;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }


    @Override
    public Movie addMovie(Movie movie) {
        if(movieRepository.findByTitle(movie.getTitle())!= null) return null;
        else{
            Genre genre = entityManager.find(Genre.class,movie.getGenre().getId());
            Certification certification = entityManager.find(Certification.class,movie.getCertification().getId());
            movie.setGenre(genre);
            movie.setCertification(certification);
            return movieRepository.save(movie);
        }
    }

    @Override
    public void deleteMovie(Long movieId) throws MovieIdNotFoundException {
            if (!movieRepository.existsById(movieId)) {
              throw new MovieIdNotFoundException();
            }
            movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> getMoviesBySearchCriteria(SearchCriteria search){

        if (search.getId() == null && search.getRating() == 0  && search.getActors() == null && search.getTitle() == null  && search.getGenreId() == null && search.getCertificationId() == null  && search.getYear() == 0 ){
            return movieRepository.findAll();
        }else {
                return movieRepository.findBySearchCriteria(
                        search.getId(),
                        search.getRating(),
                        search.getActors(),
                        search.getTitle(),
                        search.getYear(),
                        search.getCertificationId(),
                        search.getGenreId()
                );
        }
    }

    @Override
    public List<Movie> getMoviesForUserBySearchCriteria(Long userId,SearchCriteria search){

        List<Long> certList = getCertificationList(userId);
        if (search.getId() == null && search.getRating() == 0  && search.getActors() == null && search.getTitle() == null  && search.getGenreId() == null && search.getCertificationId() == null  && search.getYear() == 0 ){
             List<Movie> movies = movieRepository.findAllForUser(certList.toArray(new Long[certList.size()]));
            sendSMS(userId, movies.size());
            sendEmail(userId,movies);
            return movies;
        }else {

                List<Movie> movies = movieRepository.findBySearchCriteriaForUser(
                        search.getId(),
                        search.getRating(),
                        search.getActors(),
                        search.getTitle(),
                        search.getYear(),
                        certList.toArray(new Long[certList.size()]),
                        search.getCertificationId(),
                        search.getGenreId()
                );
                sendSMS(userId, movies.size());
                sendEmail(userId,movies);
                return movies;
        }
    }

    public void sendSMS(Long userId, int numMovies){
        User user = userRepository.findById(userId).get();
        String userPhoneNumber = "";
        if(user != null) {
            userPhoneNumber = user.getPhoneNumber();

            String accountSID = System.getenv("TWILIO_ACCOUNT_SID");
            String apiKey = System.getenv("TWILIO_API_KEY");
            String twilioPhoneNumber = System.getenv("TWILIO_PHONE_NUMBER");
            if (accountSID != "" && apiKey != "" && twilioPhoneNumber != "") {
                try {
                    Twilio.init(accountSID, apiKey);
                    Message.creator(

                            new PhoneNumber(userPhoneNumber),
                            new PhoneNumber(twilioPhoneNumber),

                            "Hi 📞" + user.getName() + " , you have got " + numMovies + " movies as your search result. " +
                                    "Please check your email for more details.")
                            .create();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void sendEmail(Long userId, List<Movie> movies)  {
        User user = userRepository.findById(userId).get();
        String userEmail = "";
        if(user != null) {
            userEmail = user.getEmail();
            String userName = user.getUserName();
            Email from = new Email("archanajl@gmail.com");
            Email to = new Email(userEmail);

            Mail mail = new Mail();
            DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
            personalization.addTo(to);
            mail.setFrom(from);
            mail.setReplyTo(to);
            personalization.addDynamicTemplateData("user", userName);
            personalization.addDynamicTemplateData("movies", getMovieTitles(movies));
            mail.addPersonalization(personalization);
            mail.setTemplateId(System.getenv("TEMPLATE_ID"));
            mail.setSubject("Your search results for movies");

            String sendGridAPIKey = System.getenv("SENDGRID_API_KEY");
            if (sendGridAPIKey != "") {
                SendGrid sg = new SendGrid(sendGridAPIKey);
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());

                    Response response = sg.api(request);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String getMovieTitles(List<Movie> movies){
        String movieTitles = "  ";
        for (Movie movie:movies
             ) {
            movieTitles += movie.getTitle() + "\n";
        }
        return movieTitles;
    }

    public List<Long> getCertificationList(Long userId){
        User user = userRepository.findById(userId).get();
        int userAge = 99;
        if(user != null) {
            userAge = user.getAge();
        }
        ArrayList<String> certNames = new ArrayList<>();

        certNames.add("U");
        certNames.add("12A");
        if (userAge >= 18){
            certNames.add("18");
            certNames.add("R");
            certNames.add("TBC");
        }
        if (userAge >= 15){
            certNames.add("15");
        }
        if (userAge >= 12){
            certNames.add("12");
        }
        if (userAge >= 8){
            certNames.add("PG");
        }

        List<Long> certList = certificationRepository.findByNames(certNames);
        return certList;
    }

}
