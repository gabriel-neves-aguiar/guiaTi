package com.guiati.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiati.backend.dtos.CursoDTO;
import com.guiati.backend.dtos.CursoUploadDTO;
import com.guiati.backend.models.Curso;
import com.guiati.backend.services.CursoService;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController {

  @Autowired
  private CursoService cursoService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> criarCurso(@ModelAttribute CursoUploadDTO cursoDTO) {
    try {
      Curso curso = cursoService.salvarCurso(cursoDTO);
      return ResponseEntity.ok("Curso salvo com ID: " + curso.getId());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<Curso>> listarCursos() {
    List<Curso> cursos = cursoService.listarTodos();
    return ResponseEntity.ok(cursos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
    CursoDTO dto = cursoService.buscarPorId(id);
    if (dto == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
    Optional<Curso> cursoExistente = cursoService.buscarPorIdEntity(id);
    if (cursoExistente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    cursoService.deletarCurso(id);
    return ResponseEntity.noContent().build();
  }

}