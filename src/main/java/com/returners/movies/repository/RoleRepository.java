package com.returners.movies.repository;

import com.returners.movies.model.Role;
import com.returners.movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findById(Long id);

}