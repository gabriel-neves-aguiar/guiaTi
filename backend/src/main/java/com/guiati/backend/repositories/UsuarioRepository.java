package com.guiati.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guiati.backend.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByEmail(String email);
}
