package com.acaiteria.acaiteria_backend.application.usecase;

import com.acaiteria.acaiteria_backend.application.ports.in.CriarTransacaoUseCase;
import com.acaiteria.acaiteria_backend.application.ports.out.TransacaoRepository;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarTransacaoService implements CriarTransacaoUseCase {

    private final TransacaoRepository transacaoRepository;

    public CriarTransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional
    public Transacao executar(Command command) {
        Transacao transacao = Transacao.nova(
                command.descricao(),
                command.valor(),
                command.tipo(),
                command.origem(),
                command.data()
        );
        return transacaoRepository.salvar(transacao);
    }
}
