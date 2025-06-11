package com.guiati.backend.dtos;

public class AulaDTO {
  private Long id;
  private String nome;
  private String arquivoPath; // usado no retorno ao Angular

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

  public String getArquivoPath() {
    return arquivoPath;
  }

  public void setArquivoPath(String arquivoPath) {
    this.arquivoPath = arquivoPath;
  }

}
