package com.acaiteria.acaiteria_backend.application.ports.in;

import java.util.UUID;

public interface ExcluirTransacaoUseCase {
    void executar(UUID id);
}
