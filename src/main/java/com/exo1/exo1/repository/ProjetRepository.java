package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Override
    @Query("SELECT DISTINCT p FROM Projet p LEFT JOIN FETCH p.tasks LEFT JOIN FETCH p.users")
    List<Projet> findAll();

    @Query("SELECT p FROM Projet p LEFT JOIN FETCH p.tasks WHERE p.id = :id")
    Optional<Projet> findByIdWithTasks(@Param("id") Long id);
}
