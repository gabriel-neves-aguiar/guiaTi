package com.guiati.backend.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Modulo {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modulo_seq")
  @SequenceGenerator(name = "modulo_seq", sequenceName = "modulo_seq", allocationSize = 1)
  private Long id;

  private String nome;

  @ManyToOne
  @JoinColumn(name = "curso_id")
  @JsonBackReference
  private Curso curso;

  @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Aula> aulas = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public List<Aula> getAulas() {
    return aulas;
  }

  public void setAulas(List<Aula> aulas) {
    this.aulas = aulas;
  }

}
