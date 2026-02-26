-- ===========================================
-- V1: Criação das tabelas iniciais
-- ===========================================

CREATE TABLE IF NOT EXISTS usuario (
                                       id      UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    email   VARCHAR(255) NOT NULL UNIQUE,
    senha   VARCHAR(255) NOT NULL,
    ativo   BOOLEAN     NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS transacoes (
                                          id            UUID           PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao     VARCHAR(255)   NOT NULL,
    valor         NUMERIC(19, 2) NOT NULL CHECK (valor > 0),
    tipo          VARCHAR(10)    NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA')),
    origem        VARCHAR(20)    NOT NULL CHECK (origem IN ('BANCO_DO_BRASIL', 'MERCADO_PAGO', 'MANUAL')),
    data          DATE           NOT NULL,
    sincronizada  BOOLEAN        NOT NULL DEFAULT FALSE,
    external_id   VARCHAR(255),
    CONSTRAINT uq_external_id UNIQUE (external_id)
    );

-- Regra: SAIDA nunca pode ter origem MERCADO_PAGO
ALTER TABLE transacoes
    ADD CONSTRAINT chk_saida_origem
        CHECK (NOT (tipo = 'SAIDA' AND origem = 'MERCADO_PAGO'));

-- Usuário padrão do sistema (senha: admin123 com BCrypt)
INSERT INTO usuario (email, senha, ativo)
VALUES (
           'admin@acaiteria.com',
           '$2a$12$K8HKjqF9oGzVm3A8GqVB7OxjFkBHGGmwUfBeFW.3TJBp5XqgS3xBe',
           TRUE
       )
    ON CONFLICT (email) DO NOTHING;