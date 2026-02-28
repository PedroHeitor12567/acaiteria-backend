package com.acaiteria.acaiteria_backend.application.usecase;

import com.acaiteria.acaiteria_backend.application.ports.in.CalcularLucroMensalUseCase;
import com.acaiteria.acaiteria_backend.application.ports.out.TransacaoRepository;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import com.acaiteria.acaiteria_backend.domain.model.ResumoMensal;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Service
public class CalcularLucroMensalService implements CalcularLucroMensalUseCase {

    private final TransacaoRepository transacaoRepository;

    public CalcularLucroMensalService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResumoMensal executar(Query query) {
        YearMonth ym = YearMonth.of(query.ano(), query.mes());
        List<Transacao> transacoes = transacaoRepository.listarPorPeriodo(
                ym.atDay(1), ym.atEndOfMonth()
        );

        BigDecimal entradas = transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.ENTRADA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saidas = transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.SAIDA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResumoMensal(entradas, saidas);
    }
}
