package com.guiati.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiati.backend.dtos.AulaDTO;
import com.guiati.backend.dtos.AulaUploadDTO;
import com.guiati.backend.dtos.CursoDTO;
import com.guiati.backend.dtos.CursoUploadDTO;
import com.guiati.backend.dtos.ModuloDTO;
import com.guiati.backend.dtos.ModuloUploadDTO;
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

  public Curso salvarCurso(CursoUploadDTO dto) throws IOException {
    Curso curso = new Curso();
    curso.setTitulo(dto.getTitulo());
    curso.setDescricao(dto.getDescricao());
    curso.setCategoria(dto.getCategoria());

    for (ModuloUploadDTO moduloDTO : dto.getModulos()) {
      Modulo modulo = new Modulo();
      modulo.setNome(moduloDTO.getNome());
      modulo.setCurso(curso);

      for (AulaUploadDTO aulaDTO : moduloDTO.getAulas()) {
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

  public CursoDTO buscarPorId(Long id) {
    Curso curso = cursoRepository.findById(id).orElse(null);
    if (curso == null)
      return null;

    CursoDTO dto = new CursoDTO();
    dto.setId(curso.getId());
    dto.setTitulo(curso.getTitulo());
    dto.setDescricao(curso.getDescricao());
    dto.setCategoria(curso.getCategoria());

    List<ModuloDTO> modulosDTO = curso.getModulos().stream().map(modulo -> {
      ModuloDTO mDto = new ModuloDTO();
      mDto.setId(modulo.getId());
      mDto.setNome(modulo.getNome());

      List<AulaDTO> aulasDTO = modulo.getAulas().stream().map(aula -> {
        AulaDTO aDto = new AulaDTO();
        aDto.setId(aula.getId());
        aDto.setNome(aula.getNome());
        aDto.setArquivoPath(aula.getArquivoPath());
        return aDto;
      }).toList();

      mDto.setAulas(aulasDTO);
      return mDto;
    }).toList();

    dto.setModulos(modulosDTO);
    return dto;
  }

  public Optional<Curso> buscarPorIdEntity(Long id) {
    return cursoRepository.findById(id);
  }

  public void deletarCurso(Long id) {
    Optional<Curso> cursoOpt = cursoRepository.findByIdComModulosEAulas(id);
    if (cursoOpt.isPresent()) {
      Curso curso = cursoOpt.get();
      for (Modulo modulo : curso.getModulos()) {
        if (modulo.getAulas() != null) {
          for (Aula aula : modulo.getAulas()) {
            try {
              arquivoService.deletarArquivo(aula.getArquivoPath());
            } catch (IOException e) {
              System.err.println("Erro ao deletar arquivo: " + aula.getArquivoPath());
            }
          }
        }
      }
      cursoRepository.deleteById(id);
    }
  }

}
