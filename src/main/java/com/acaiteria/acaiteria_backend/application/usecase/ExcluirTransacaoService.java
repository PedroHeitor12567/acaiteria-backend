package com.acaiteria.acaiteria_backend.application.usecase;

import com.acaiteria.acaiteria_backend.application.ports.in.ExcluirTransacaoUseCase;
import com.acaiteria.acaiteria_backend.application.ports.out.TransacaoRepository;
import com.acaiteria.acaiteria_backend.domain.exception.RecursoNaoEncontradoException;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ExcluirTransacaoService implements ExcluirTransacaoUseCase {

    private final TransacaoRepository transacaoRepository;

    public ExcluirTransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional
    public void executar(UUID id) {
        Transacao transacao = transacaoRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada: " + id));

        transacao.validarParaExclusao();

        transacaoRepository.excluir(id);
    }
}
