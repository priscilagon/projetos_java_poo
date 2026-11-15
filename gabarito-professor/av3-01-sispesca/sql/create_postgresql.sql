-- ============================================================
-- SisPesca — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS biometria CASCADE;
DROP TABLE IF EXISTS lote_peixe CASCADE;
DROP TABLE IF EXISTS criadouro CASCADE;
DROP TABLE IF EXISTS cooperado CASCADE;

CREATE TABLE cooperado (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    propriedade VARCHAR(150)
);

-- tipo: TANQUE_REDE ou VIVEIRO
CREATE TABLE criadouro (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL,
    especie_criada VARCHAR(60) NOT NULL,
    volume_m3 NUMERIC(12,2),
    area_m2 NUMERIC(12,2)
);

CREATE TABLE lote_peixe (
    id SERIAL PRIMARY KEY,
    identificador VARCHAR(30) NOT NULL UNIQUE,
    cooperado_id INTEGER NOT NULL,
    criadouro_id INTEGER NOT NULL,
    data_povoamento DATE NOT NULL,
    quantidade_alevinos INTEGER NOT NULL,
    peso_inicial_medio_gramas NUMERIC(12,2) NOT NULL,
    racao_total_consumida_kg NUMERIC(12,2) NOT NULL DEFAULT 0,
    FOREIGN KEY (cooperado_id) REFERENCES cooperado(id),
    FOREIGN KEY (criadouro_id) REFERENCES criadouro(id)
);

CREATE TABLE biometria (
    id SERIAL PRIMARY KEY,
    lote_id INTEGER NOT NULL,
    data DATE NOT NULL,
    peso_medio_gramas NUMERIC(12,2) NOT NULL,
    comprimento_medio_cm NUMERIC(12,2) NOT NULL,
    FOREIGN KEY (lote_id) REFERENCES lote_peixe(id)
);
