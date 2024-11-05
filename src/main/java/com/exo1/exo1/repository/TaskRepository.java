package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Fetch Task with associated User (One-to-One)
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.user WHERE t.id = :id")
    Optional<Task> findByIdWithUser(@Param("id") Long id);

    // Fetch Task with associated Projet (Many-to-One)
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.projet WHERE t.id = :id")
    Optional<Task> findByIdWithProjet(@Param("id") Long id);

    // Fetch all Tasks with associated User and Projet
    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.user LEFT JOIN FETCH t.projet")
    List<Task> findAllWithUserAndProjet();
}
