package com.returners.movies.repository;

import com.returners.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query("select m from Movie m " +
            "where :actor IN m.actors ")
            //"where m.actors @> '{Emma Stone}'::text[] ")
    List<Movie> findByActors( @Param("actor")String actor);

    List<Movie> findByIdOrRatingOrTitleOrYearOrCertificationIdOrGenreId(
            Long id,
            int rating,
            String title,
            int year,
            Long certificationId,
            Long genreId
                );

    @Query( "select m from Movie m " +
            "INNER JOIN Certification  c ON m.certification.id = c.id " +
            "INNER JOIN Genre g ON m.genre.id = g.id "+
            "where m.id = :id or m.rating = :rating " +
                //"or m.actors @> '{Emma Stone}'::text[] " +
                "or m.title = :title or m.year = :year " +
               "AND c.id IN :ids " +
               "or g.id = :genreId"
    )
    List<Movie> findBySearchCriteria(
            @Param("id") Long id,
            @Param("rating") int rating,
            //@Param("actors") String actors,
            @Param("title") String title,
            @Param("year") int year,
            @Param("ids") Long[] ids,
            @Param("genreId") Long genreId
    );
    Movie findByTitle(String title);
}
