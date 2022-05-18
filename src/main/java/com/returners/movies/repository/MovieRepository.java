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
            "where (:id IS NULL OR m.id = :id) " +
            "AND (:rating = 0 OR m.rating = :rating) " +
            "AND (:actor IS NULL OR array_to_string(actors, ',') like '%' || cast(:actor as text) || '%') " +
            "AND (:title IS NULL OR m.title = :title) " +
            "AND (:year = 0 OR m.year = :year) " +
            "AND (:certificationId IS NULL OR c.id = :certificationId) " +
            "AND (:genreId IS NULL OR g.id = :genreId)"
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
                "AND (:id IS NULL OR m.id = :id) " +
                "AND (:rating = 0 OR m.rating = :rating) " +
                "AND (:actor IS NULL OR array_to_string(actors, ',') like '%' || cast(:actor as text) || '%') " +
                "AND (:title IS NULL OR m.title = :title) " +
                "AND (:year = 0 OR m.year = :year) " +
                "AND (:certificationId IS NULL OR c.id = :certificationId) " +
                "AND (:genreId IS NULL OR g.id = :genreId)"
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
