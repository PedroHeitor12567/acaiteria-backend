package com.acaiteria.acaiteria_backend.application.ports.in;

import com.acaiteria.acaiteria_backend.domain.model.ResumoMensal;

public interface CalcularLucroMensalUseCase {

    record Query(int mes, int ano) {}

    ResumoMensal executar(Query query);
}
