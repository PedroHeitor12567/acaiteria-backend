package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransacaoJpaRepository extends JpaRepository<TransacaoEntity, UUID> {

    boolean existsByExternalId(String externalId);

    List<TransacaoEntity> findAllByDataBetweenOrderByDataDesc(LocalDate inicio, LocalDate fim);
}
