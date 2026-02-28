package com.acaiteria.acaiteria_backend.application.ports.in;

import java.util.UUID;

public interface ProcessarWebhookMercadoPagoUseCase {

    record Command(String externalId, String descricao, String valorBruto, String data) {}

    void executar(UUID id);
}
