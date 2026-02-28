package com.acaiteria.acaiteria_backend.application.ports.in;

import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.util.List;

public interface ListarTransacoesUseCase {

    record Query(Integer mes, Integer ano) {}

    List<Transacao> executar(Query query);
}
