package com.returners.movies.repository;

import com.returners.movies.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification,Long> {

}
