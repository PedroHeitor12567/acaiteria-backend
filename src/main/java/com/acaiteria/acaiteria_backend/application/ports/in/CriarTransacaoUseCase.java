package com.acaiteria.acaiteria_backend.application.ports.in;

import com.acaiteria.acaiteria_backend.domain.enums.OrigemTransacao;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CriarTransacaoUseCase {

    record Command(
            String descricao,
            BigDecimal valor,
            TipoTransacao tipo,
            OrigemTransacao origem,
            LocalDate data
    ) {}

    Transacao executar(Command command);
}
