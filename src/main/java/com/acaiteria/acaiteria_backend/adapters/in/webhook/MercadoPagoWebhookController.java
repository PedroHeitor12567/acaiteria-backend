package com.acaiteria.acaiteria_backend.adapters.in.webhook;

import com.acaiteria.acaiteria_backend.application.ports.in.ProcessarWebhookMercadoPagoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook/mercadopago")
public class MercadoPagoWebhookController {

    private final ProcessarWebhookMercadoPagoUseCase processarWebhookUseCase;

    public MercadoPagoWebhookController(ProcessarWebhookMercadoPagoUseCase processarWebhookUseCase) {
        this.processarWebhookUseCase = processarWebhookUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> receber(@RequestBody Map<String, Object> payload) {
        // O MP envia um payload com o id do pagamento para consultar via API
        String externalId = String.valueOf(payload.get("data_id"));
        String descricao = "Pagamento Mercado Pago";
        String valorBruto = String.valueOf(payload.getOrDefault("amount", "0"));
        String data = String.valueOf(payload.getOrDefault("date_created", ""));

        var command = new ProcessarWebhookMercadoPagoUseCase.Command(externalId, descricao, valorBruto, data);
        processarWebhookUseCase.executar(command);

        return ResponseEntity.ok().build();
    }
}
