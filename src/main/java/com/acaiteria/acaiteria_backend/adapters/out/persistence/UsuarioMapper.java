package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import com.acaiteria.acaiteria_backend.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toDomain(UsuarioEntity entity) {
        return Usuario.reconstruir(
                entity.getId(),
                entity.getEmail(),
                entity.getSenha(),
                entity.isAtivo()
        );
    }
}