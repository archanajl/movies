package com.returners.movies.repository;

import com.returners.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query( "select m from Movie m " +
            "INNER JOIN Certification  c ON m.certification.id = c.id " +
            "INNER JOIN Genre g ON m.genre.id = g.id "+
            "where m.id = :id " +
            "or m.rating = :rating " +
            "or array_to_string(actors, ',') like '%' || cast(:actor as text) || '%' " +
            "or m.title = :title " +
            "or m.year = :year " +
            "or c.id = :certificationId " +
            "or g.id = :genreId"
    )
    List<Movie> findBySearchCriteria(
            @Param("id") Long id,
            @Param("rating") int rating,
            @Param("actor") String actor,
            @Param("title") String title,
            @Param("year") int year,
            @Param("certificationId") Long certificationId,
            @Param("genreId") Long genreId
    );

    @Query( "select m from Movie m " +
            "INNER JOIN Certification  c ON m.certification.id = c.id " +
            "INNER JOIN Genre g ON m.genre.id = g.id "+
            "where (c.id IN :ids) " +
                "AND (m.id = :id " +
                "or m.rating = :rating " +
                "or array_to_string(actors, ',') like '%' || cast(:actor as text) || '%' " +
                "or m.title = :title " +
                "or m.year = :year " +
                "or c.id = :certificationId " +
                "or g.id = :genreId)"
    )
    List<Movie> findBySearchCriteriaForUser(
            @Param("id") Long id,
            @Param("rating") int rating,
            @Param("actor") String actor,
            @Param("title") String title,
            @Param("year") int year,
            @Param("ids") Long[] ids,
            @Param("certificationId") Long certificationId,
            @Param("genreId") Long genreId
    );

    @Query( "select m from Movie m " +
            "INNER JOIN Certification  c ON m.certification.id = c.id " +
            "INNER JOIN Genre g ON m.genre.id = g.id "+
            "where c.id IN :ids")
    List<Movie> findAllForUser(@Param("ids") Long[] ids);

    Movie findByTitle(String title);
}
