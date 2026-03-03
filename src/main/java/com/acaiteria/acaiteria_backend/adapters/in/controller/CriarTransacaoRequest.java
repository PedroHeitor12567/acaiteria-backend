package com.acaiteria.acaiteria_backend.adapters.in.controller;

import com.acaiteria.acaiteria_backend.domain.enums.OrigemTransacao;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CriarTransacaoRequest(

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser maior do que zero")
        BigDecimal valor,

        @NotNull(message = "Tipo é obrigatório")
        TipoTransacao tipo,
        
        @NotNull(message = "Origem é obrigatória")
        OrigemTransacao origem,

        @NotNull(message = "Data é obrigatória")
        LocalDate data
) {}
