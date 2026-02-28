package com.acaiteria.acaiteria_backend.application.ports.out;

import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransacaoRepository {

    Transacao salvar(Transacao transacao);

    Optional<Transacao> buscarPorId(UUID id);

    boolean existePorExternalId(String externalId);

    List<Transacao> listarPorPeriodo(LocalDate inicio, LocalDate fim);

    void excluir(UUID id);
}
