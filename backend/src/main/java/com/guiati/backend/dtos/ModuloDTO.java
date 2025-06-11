package com.guiati.backend.dtos;

import java.util.List;

public class ModuloDTO {

  private Long id;
  private String nome;
  private List<AulaDTO> aulas;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<AulaDTO> getAulas() {
    return aulas;
  }

  public void setAulas(List<AulaDTO> aulas) {
    this.aulas = aulas;
  }

}
