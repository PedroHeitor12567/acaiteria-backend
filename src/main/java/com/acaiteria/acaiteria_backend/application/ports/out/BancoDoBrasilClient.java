package com.acaiteria.acaiteria_backend.application.ports.out;

import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.util.List;

public interface BancoDoBrasilClient {
    List<Transacao> buscarTransacoesRecentes();
}
