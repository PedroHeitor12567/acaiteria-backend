package com.acaiteria.acaiteria_backend.domain.model;

import com.acaiteria.acaiteria_backend.domain.enums.OrigemTransacao;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Transacao {

    private UUID id;
    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private OrigemTransacao origem;
    private LocalDate data;
    private boolean sincronizada;
    private String externalId;

    public Transacao() {}

    public static Transacao nova(
            String descricao,
            BigDecimal valor,
            TipoTransacao tipo,
            OrigemTransacao origem,
            LocalDate data
    ) {
        Transacao t = new Transacao();
        t.id = UUID.randomUUID();
        t.descricao = descricao;
        t.sincronizada = false;
        t.externalId = null;
        t.definirValor(valor);
        t.definirTipoEOrigem(tipo, origem);
        t.data = data;
        return t;
    }

    public static Transacao sincronizada(
            String descricao,
            BigDecimal valor,
            TipoTransacao tipo,
            OrigemTransacao origem,
            LocalDate data,
            String externalId
    ) {
        Transacao t = nova(descricao, valor, tipo, origem, data);
        t.sincronizada = true;
        t.externalId = externalId;
        return t;
    }

    public static Transacao reconstruir(
            UUID id,
            String descricao,
            BigDecimal valor,
            TipoTransacao tipo,
            OrigemTransacao origem,
            LocalDate data,
            boolean sincronizada,
            String externalId
    ) {
        Transacao t = new Transacao();
        t.id = id;
        t.descricao = descricao;
        t.valor = valor;
        t.tipo = tipo;
        t.origem = origem;
        t.data = data;
        t.sincronizada = sincronizada;
        t.externalId = externalId;
        return t;
    }

    private void definirValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("O valor da transação deve ser maior que zero.");
        }
        this.valor = valor;
    }

    private void definirTipoEOrigem(TipoTransacao tipo, OrigemTransacao origem) {
        if (tipo == TipoTransacao.SAIDA && origem == OrigemTransacao.MERCADO_PAGO) {
            throw new RegraDeNegocioException("Transações de SAÍDA não podem ter origem MERCADO_PAGO.");
        }
        this.tipo = tipo;
        this.origem = origem;
    }

    public void validarParaExclusao() {
        if (this.sincronizada) {
            throw new RegraDeNegocioException("Transações sincronizadas não podem ser excluídas.");
        }
    }
}
