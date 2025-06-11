package com.guiati.backend.dtos;

import java.util.List;

public class ModuloUploadDTO {
  private String nome;
  private List<AulaUploadDTO> aulas;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<AulaUploadDTO> getAulas() {
    return aulas;
  }

  public void setAulas(List<AulaUploadDTO> aulas) {
    this.aulas = aulas;
  }
}
