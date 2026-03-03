package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import com.acaiteria.acaiteria_backend.application.ports.out.UsuarioRepository;
import com.acaiteria.acaiteria_backend.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaAdapter implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioMapper mapper;

    public UsuarioJpaAdapter(UsuarioJpaRepository jpaRepository, UsuarioMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }
}
