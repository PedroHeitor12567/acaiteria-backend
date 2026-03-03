package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public TransacaoEntity toEntity(Transacao transacao) {
        return new TransacaoEntity(
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

    public Transacao toDomain(TransacaoEntity entity) {
        return Transacao.reconstruir(
                entity.getId(),
                entity.getDescricao(),
                entity.getValor(),
                entity.getTipo(),
                entity.getOrigem(),
                entity.getData(),
                entity.isSincronizada(),
                entity.getExternalId()
        );
    }
}
