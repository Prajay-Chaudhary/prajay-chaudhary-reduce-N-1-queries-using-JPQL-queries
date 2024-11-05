package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    // Fetch Projet with associated Users (Many-to-Many)
    @Query("SELECT p FROM Projet p LEFT JOIN FETCH p.users WHERE p.id = :id")
    Optional<Projet> findByIdWithUsers(@Param("id") Long id);

    // Fetch Projet with associated Tasks (One-to-Many)
    @Query("SELECT p FROM Projet p LEFT JOIN FETCH p.tasks WHERE p.id = :id")
    Optional<Projet> findByIdWithTasks(@Param("id") Long id);

    // Fetch Projet with both Users and Tasks to avoid multiple queries
    @Query("SELECT p FROM Projet p LEFT JOIN FETCH p.users LEFT JOIN FETCH p.tasks WHERE p.id = :id")
    Optional<Projet> findByIdWithUsersAndTasks(@Param("id") Long id);

    // Fetch all Projets with associated Users and Tasks
    @Query("SELECT DISTINCT p FROM Projet p LEFT JOIN FETCH p.users LEFT JOIN FETCH p.tasks")
    List<Projet> findAllWithUsersAndTasks();
}
