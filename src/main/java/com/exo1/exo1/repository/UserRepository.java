package com.exo1.exo1.repository;

import com.exo1.exo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Fetch User with both Task and Projects to avoid multiple queries
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.task LEFT JOIN FETCH u.projets WHERE u.id = :id")
    Optional<User> findByIdWithTaskAndProjects(@Param("id") Long id);

    // Fetch all Users with associated Task and Projects, useful to avoid N+1 on lists
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.task LEFT JOIN FETCH u.projets")
    List<User> findAllWithTaskAndProjects();
}
