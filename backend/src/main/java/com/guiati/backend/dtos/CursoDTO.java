package com.guiati.backend.dtos;

import java.util.List;

public class CursoDTO {
  private Long id;
  private String titulo;
  private String descricao;
  private String categoria;
  private List<ModuloDTO> modulos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public List<ModuloDTO> getModulos() {
    return modulos;
  }

  public void setModulos(List<ModuloDTO> modulos) {
    this.modulos = modulos;
  }
}
