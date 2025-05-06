package com.guiati.backend.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiati.backend.dtos.AulaDTO;
import com.guiati.backend.dtos.CursoDTO;
import com.guiati.backend.dtos.ModuloDTO;
import com.guiati.backend.models.Aula;
import com.guiati.backend.models.Curso;
import com.guiati.backend.models.Modulo;
import com.guiati.backend.repositories.CursoRepository;

@Service
public class CursoService {

  @Autowired
  private CursoRepository cursoRepository;

  @Autowired
  private ArquivoService arquivoService;

  public Curso salvarCurso(CursoDTO dto) throws IOException {
    Curso curso = new Curso();
    curso.setTitulo(dto.getTitulo());
    curso.setDescricao(dto.getDescricao());
    curso.setCategoria(dto.getCategoria());

    for (ModuloDTO moduloDTO : dto.getModulos()) {
      Modulo modulo = new Modulo();
      modulo.setNome(moduloDTO.getNome());
      modulo.setCurso(curso);

      for (AulaDTO aulaDTO : moduloDTO.getAulas()) {
        Aula aula = new Aula();
        aula.setNome(aulaDTO.getNome());
        aula.setArquivoPath(arquivoService.salvarArquivo(aulaDTO.getArquivo()));
        aula.setModulo(modulo);
        modulo.getAulas().add(aula);
      }

      curso.getModulos().add(modulo);
    }

    return cursoRepository.save(curso);
  }

  public List<Curso> listarTodos() {
    return cursoRepository.findAll();
  }
}
