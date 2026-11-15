-- ============================================================
-- GuardiaoIgarape — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS participacao CASCADE;
DROP TABLE IF EXISTS acao_ambiental CASCADE;
DROP TABLE IF EXISTS voluntario CASCADE;

CREATE TABLE voluntario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    contato VARCHAR(100)
);

-- tipo: LIMPEZA, PLANTIO ou EDUCACAO_AMBIENTAL
CREATE TABLE acao_ambiental (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(25) NOT NULL,
    local VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    duracao_horas NUMERIC(12,2) NOT NULL
);

CREATE TABLE participacao (
    id SERIAL PRIMARY KEY,
    voluntario_id INTEGER NOT NULL,
    acao_id INTEGER NOT NULL,
    FOREIGN KEY (voluntario_id) REFERENCES voluntario(id),
    FOREIGN KEY (acao_id) REFERENCES acao_ambiental(id)
);
