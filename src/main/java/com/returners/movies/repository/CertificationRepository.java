package com.returners.movies.repository;

import com.returners.movies.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification,Long> {

    Certification findByName(String name);

    @Query( "select id from Certification c where c.name IN :names" )
    List<Long> findByNames(@Param("names") List<String> certificationNameList);

}
