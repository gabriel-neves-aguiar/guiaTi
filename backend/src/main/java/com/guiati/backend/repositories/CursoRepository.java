package com.guiati.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guiati.backend.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

  @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.modulos m LEFT JOIN FETCH m.aulas WHERE c.id = :id")
  Optional<Curso> findByIdComModulosEAulas(@Param("id") Long id);
}
