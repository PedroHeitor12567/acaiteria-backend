package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import com.acaiteria.acaiteria_backend.application.ports.out.TransacaoRepository;
import com.acaiteria.acaiteria_backend.domain.exception.RecursoNaoEncontradoException;
import com.acaiteria.acaiteria_backend.domain.model.Transacao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransacaoJpaAdapter implements TransacaoRepository {

    private final TransacaoJpaRepository jpaRepository;
    private final TransacaoMapper mapper;

    public TransacaoJpaAdapter(TransacaoJpaRepository jpaRepository, TransacaoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Transacao salvar(Transacao transacao) {
        TransacaoEntity entity = mapper.toEntity(transacao);
        TransacaoEntity saved =  jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Transacao> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existePorExternalId(String externalId) {
        return jpaRepository.existsByExternalId(externalId);
    }

    @Override
    public List<Transacao> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return jpaRepository
                .findAllByDataBetweenOrderByDataDesc(inicio, fim)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void excluir(UUID id) {
        if (!jpaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Transação não encontrada: " + id);
        }
        jpaRepository.deleteById(id);
    }
}
