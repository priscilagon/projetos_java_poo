-- ============================================================
-- MonitoraRioNegro — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS medicao CASCADE;
DROP TABLE IF EXISTS estacao_monitoramento CASCADE;

CREATE TABLE estacao_monitoramento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE,
    municipio VARCHAR(80) NOT NULL
);

CREATE TABLE medicao (
    id SERIAL PRIMARY KEY,
    estacao_id INTEGER NOT NULL,
    data DATE NOT NULL,
    nivel_metros NUMERIC(12,2) NOT NULL,
    FOREIGN KEY (estacao_id) REFERENCES estacao_monitoramento(id)
);
