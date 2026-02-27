package com.acaiteria.acaiteria_backend.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResumoMensal {

    private final BigDecimal totalEntradas;
    private final BigDecimal totalSaidas;
    private final BigDecimal lucro;

    public ResumoMensal(BigDecimal totalEntradas, BigDecimal totalSaidas) {
        this.totalEntradas = totalEntradas != null ? totalEntradas : BigDecimal.ZERO;
        this.totalSaidas = totalSaidas != null ? totalSaidas : BigDecimal.ZERO;
        this.lucro = this.totalEntradas.subtract(this.totalSaidas);
    }
}
