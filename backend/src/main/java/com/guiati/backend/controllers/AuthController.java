package com.guiati.backend.controllers;

import com.guiati.backend.models.Usuario;
import com.guiati.backend.repositories.UsuarioRepository;
import com.guiati.backend.services.AuthService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  private final UsuarioRepository usuarioRepository;

  @Autowired
  private AuthService authService;

  AuthController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @PostMapping("/cadastro")
  public ResponseEntity<Map<String, String>> cadastrar(@RequestBody Usuario usuario) {
    return authService.cadastrar(usuario);
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
    return authService.login(usuario);
  }

  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> listarUsuarios() {
    List<Usuario> usuarios = authService.listarUsuarios();
    return ResponseEntity.ok(usuarios);
  }

  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<Map<String, String>> excluirUsuario(@PathVariable Long id) {
    boolean deleted = authService.deleteUsuario(id);
    if (deleted) {
      return ResponseEntity.ok(Map.of("mensagem", "Usuário excluído com sucesso"));
    } else {
      return ResponseEntity.badRequest().body(Map.of("mensagem", "Erro: Usuário não encontrado"));
    }
  }
}
