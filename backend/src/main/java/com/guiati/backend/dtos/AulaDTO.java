package com.guiati.backend.dtos;

import org.springframework.web.multipart.MultipartFile;

public class AulaDTO {
  private String nome;
  private MultipartFile arquivo;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public MultipartFile getArquivo() {
    return arquivo;
  }

  public void setArquivo(MultipartFile arquivo) {
    this.arquivo = arquivo;
  }

}
