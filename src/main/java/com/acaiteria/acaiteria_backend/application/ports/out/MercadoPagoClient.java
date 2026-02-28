package com.acaiteria.acaiteria_backend.application.ports.out;

import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.util.Optional;

public interface MercadoPagoClient {
    Optional<Transacao> buscarPagamentoPorId(String externalId);
}
