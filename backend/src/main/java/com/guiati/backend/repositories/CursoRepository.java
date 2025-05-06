package com.guiati.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guiati.backend.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
