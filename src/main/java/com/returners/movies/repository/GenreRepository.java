package com.returners.movies.repository;

import com.returners.movies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository <Genre,Long>{
}
