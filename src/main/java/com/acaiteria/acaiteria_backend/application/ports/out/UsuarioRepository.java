package com.acaiteria.acaiteria_backend.application.ports.out;

import com.acaiteria.acaiteria_backend.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorEmail(String email);
}
