package com.returners.movies.repository;

import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByActors(String[] actors);
    List<Movie> findByIdOrRatingOrTitleOrYearOrCertificationOrGenre(
            Long id,
            int Rating,
            String title,
            int year,
            Optional<Certification> cert,
            Optional<Genre> genre
                );

    @Query( "select m from Movie m where m.id = :id or m.rating = :rating " +
                //"or m.actors @> '{Emma Stone}'::text[] " +
                "or m.title = :title or m.year = :year " +
               "or m.certification = :cert " +
               "or m.genre = :genre"
    )
    List<Movie> findBySearchCriteria(
            @Param("id") Long id,
            @Param("rating") int rating,
            //@Param("actors") String actors,
            @Param("title") String title,
            @Param("year") int year,
            @Param("cert") Optional<Certification> cert,
            @Param("genre") Optional<Genre> genre
    );
}
