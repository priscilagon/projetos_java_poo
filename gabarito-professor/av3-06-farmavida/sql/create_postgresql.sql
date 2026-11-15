-- ============================================================
-- FarmaVida — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS movimentacao_estoque CASCADE;
DROP TABLE IF EXISTS lote CASCADE;
DROP TABLE IF EXISTS medicamento CASCADE;

CREATE TABLE medicamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    principio_ativo VARCHAR(100),
    estoque_minimo INTEGER NOT NULL
);

CREATE TABLE lote (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    medicamento_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    validade DATE NOT NULL,
    FOREIGN KEY (medicamento_id) REFERENCES medicamento(id)
);

-- tipo: ENTRADA ou SAIDA
CREATE TABLE movimentacao_estoque (
    id SERIAL PRIMARY KEY,
    lote_id INTEGER NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    quantidade INTEGER NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (lote_id) REFERENCES lote(id)
);
