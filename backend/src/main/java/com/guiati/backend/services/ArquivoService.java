package com.guiati.backend.services;

import com.guiati.backend.dtos.CursoDTO;
import com.guiati.backend.models.Aula;
import com.guiati.backend.models.Curso;
import com.guiati.backend.models.Modulo;
import com.guiati.backend.repositories.CursoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ArquivoService {
  private final Path pastaUpload = Paths.get("C:/Users/Public/uploads/aulas");

  public ArquivoService() throws IOException {
    Files.createDirectories(pastaUpload);
  }

  public String salvarArquivo(MultipartFile file) throws IOException {
    String nomeArquivo = UUID.randomUUID() + "-" + file.getOriginalFilename();
    Path destino = pastaUpload.resolve(nomeArquivo);
    Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
    return nomeArquivo;
  }

  public void deletarArquivo(String nomeArquivo) throws IOException {
    Path caminho = pastaUpload.resolve(nomeArquivo);
    Files.deleteIfExists(caminho);
  }

  // public void deletarArquivo(String caminhoRelativo) throws IOException {
  // if (caminhoRelativo != null && !caminhoRelativo.isBlank()) {
  // Path caminhoCompleto = Paths.get(caminhoRelativo);
  // if (Files.exists(caminhoCompleto)) {
  // Files.delete(caminhoCompleto);
  // } else {
  // System.out.println("Arquivo não encontrado para deletar: " +
  // caminhoCompleto.toString());
  // }
  // }
  // }

  public Path getCaminhoArquivo(String nomeArquivo) {
    return pastaUpload.resolve(nomeArquivo);
  }
}