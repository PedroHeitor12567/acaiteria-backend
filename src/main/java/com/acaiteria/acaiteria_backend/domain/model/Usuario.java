package com.acaiteria.acaiteria_backend.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Usuario {

    private UUID id;
    private String email;
    private String senha;
    private boolean ativo;

    private Usuario() {}

    public static Usuario reconstruir(UUID id, String email, String senha, boolean ativo) {
        Usuario u = new Usuario();
        u.id = id;
        u.email = email;
        u.senha = senha;
        u.ativo = ativo;
        return u;
    }
}
