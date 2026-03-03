package com.acaiteria.acaiteria_backend.adapters.out.persistence;

import com.acaiteria.acaiteria_backend.domain.enums.OrigemTransacao;
import com.acaiteria.acaiteria_backend.domain.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoTransacao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrigemTransacao origem;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private boolean sincronizada;

    @Column(name = "external_id", unique = true)
    private String externalId;
}
