package com.acaiteria.acaiteria_backend.application.usecase;

import com.acaiteria.acaiteria_backend.application.ports.in.ListarTransacoesUseCase;
import com.acaiteria.acaiteria_backend.application.ports.out.TransacaoRepository;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
public class ListarTransacoesService implements ListarTransacoesUseCase {

    private final TransacaoRepository transacaoRepository;

    public ListarTransacoesService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transacao> executar(Query query) {
        if (query.mes() != null && query.ano() != null) {
            YearMonth ym = YearMonth.of(query.ano(), query.mes());
            return transacaoRepository.listarPorPeriodo(ym.atDay(1), ym.atEndOfMonth());
        }

        YearMonth atual = YearMonth.now();
        return transacaoRepository.listarPorPeriodo(atual.atDay(1), atual.atEndOfMonth());
    }
}
