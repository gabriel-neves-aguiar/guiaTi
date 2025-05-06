package com.guiati.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Aula {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aula_seq")
  @SequenceGenerator(name = "aula_seq", sequenceName = "aula_seq", allocationSize = 1)
  private Long id;

  private String nome;
  private String arquivoPath;

  @ManyToOne
  @JoinColumn(name = "modulo_id")
  @JsonBackReference
  private Modulo modulo;

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getArquivoPath() {
    return arquivoPath;
  }

  public void setArquivoPath(String arquivoPath) {
    this.arquivoPath = arquivoPath;
  }

  public Modulo getModulo() {
    return modulo;
  }

  public void setModulo(Modulo modulo) {
    this.modulo = modulo;
  }

  // Getters e Setters

}
