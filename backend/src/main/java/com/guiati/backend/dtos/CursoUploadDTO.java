package com.guiati.backend.dtos;

import java.util.List;

public class CursoUploadDTO {
  private String titulo;
  private String descricao;
  private String categoria;
  private List<ModuloUploadDTO> modulos;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public List<ModuloUploadDTO> getModulos() {
    return modulos;
  }

  public void setModulos(List<ModuloUploadDTO> modulos) {
    this.modulos = modulos;
  }
}
