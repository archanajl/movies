package com.returners.movies.repository;

import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    //List<Movie> findByActors(List<String> actors);
    List<Movie> findByIdOrRatingOrTitleOrYearOrCertificationOrGenre(
            Long id,
            int Rating,
            String title,
            int year,
            Optional<Certification> cert,
            Optional<Genre> genre
                );
}
