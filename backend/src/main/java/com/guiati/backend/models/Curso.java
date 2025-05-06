package com.guiati.backend.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq")
  @SequenceGenerator(name = "curso_seq", sequenceName = "curso_seq", allocationSize = 1)
  private Long id;

  private String titulo;
  private String descricao;
  private String categoria;

  @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Modulo> modulos = new ArrayList<>();

  // getters e setters

  public Long getId() {
    return id;
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

  public List<Modulo> getModulos() {
    return modulos;
  }

  public void setModulos(List<Modulo> modulos) {
    this.modulos = modulos;
  }

}
