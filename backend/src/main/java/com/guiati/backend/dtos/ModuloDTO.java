package com.guiati.backend.dtos;

import java.util.List;

public class ModuloDTO {

  private String nome;
  private List<AulaDTO> aulas;

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
