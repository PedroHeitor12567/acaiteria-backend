package com.acaiteria.acaiteria_backend.adapters.in.controller;

import com.acaiteria.acaiteria_backend.domain.model.ResumoMensal;

import java.math.BigDecimal;

public record ResumoMensalResponse(
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal lucro
) {
    public static ResumoMensalResponse from(ResumoMensal resumo) {
        return new ResumoMensalResponse(
                resumo.getTotalEntradas(),
                resumo.getTotalSaidas(),
                resumo.getLucro()
        );
    }
}
