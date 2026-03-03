package com.acaiteria.acaiteria_backend.adapters.in.controller;

import com.acaiteria.acaiteria_backend.domain.enums.OrigemTransacao;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoResponse(
        UUID id,
        String descricao,
        BigDecimal valor,
        TipoTransacao tipo,
        OrigemTransacao origem,
        LocalDate data,
        boolean sincronizada,
        String externalId
) {
    public static TransacaoResponse from(Transacao transacao) {
        return new TransacaoResponse(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getOrigem(),
                transacao.getData(),
                transacao.isSincronizada(),
                transacao.getExternalId()
        );
    }
}
