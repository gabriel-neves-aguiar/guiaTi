package com.guiati.backend.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.guiati.backend.models.Usuario;
import com.guiati.backend.repositories.UsuarioRepository;

@Service
public class AuthService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public ResponseEntity<Map<String, String>> cadastrar(Usuario usuario) {
    if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
      return ResponseEntity.badRequest()
          .body(Map.of("mensagem", "Erro: Email já cadastrado"));
    }

    usuarioRepository.save(usuario);
    return ResponseEntity.ok(Map.of("mensagem", "Usuário cadastrado com sucesso"));
  }

  public ResponseEntity<Map<String, String>> login(Usuario usuario) {
    Usuario encontrado = usuarioRepository.findByEmail(usuario.getEmail());
    if (encontrado == null || !encontrado.getSenha().equals(usuario.getSenha())) {
      return ResponseEntity.badRequest()
          .body(Map.of("mensagem", "Erro: Email ou senha inválidos"));
    }

    return ResponseEntity.ok(Map.of("mensagem", "Login realizado com sucesso"));

  }

  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll(); // Retorna todos os usuários
  }

  public boolean deleteUsuario(Long id) {
    if (usuarioRepository.existsById(id)) {
      usuarioRepository.deleteById(id);
      return true; // Usuário excluído com sucesso
    }
    return false; // Usuário não encontrado
  }
}
