package com.guiati.backend.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiati.backend.services.ArquivoService;

@RestController
@RequestMapping("/api/aulas")
@CrossOrigin(origins = "http://localhost:4200")
public class AulaController {

  private final ArquivoService arquivoService;

  public AulaController(ArquivoService arquivoService) {
    this.arquivoService = arquivoService;
  }

  private final Path pastaUpload = Paths.get("C:/Users/Public/uploads/aulas");

  @GetMapping("/video/{nomeArquivo}")
  public ResponseEntity<Resource> servirVideo(@PathVariable String nomeArquivo) throws IOException {
    Path caminho = pastaUpload.resolve(nomeArquivo);
    Resource recurso = new UrlResource(caminho.toUri());

    if (!recurso.exists()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaTypeFactory.getMediaType(recurso).orElse(MediaType.APPLICATION_OCTET_STREAM))
        .body(recurso);
  }
}
