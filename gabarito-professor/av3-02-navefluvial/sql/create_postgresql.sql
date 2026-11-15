-- ============================================================
-- NaveFluvial — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS passagem CASCADE;
DROP TABLE IF EXISTS viagem CASCADE;
DROP TABLE IF EXISTS rota CASCADE;
DROP TABLE IF EXISTS embarcacao CASCADE;

CREATE TABLE embarcacao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE,
    capacidade_rede INTEGER NOT NULL,
    capacidade_camarote INTEGER NOT NULL
);

CREATE TABLE rota (
    id SERIAL PRIMARY KEY,
    origem VARCHAR(60) NOT NULL,
    destino VARCHAR(60) NOT NULL,
    distancia_km NUMERIC(12,2) NOT NULL,
    preco_base_passagem NUMERIC(12,2) NOT NULL
);

CREATE TABLE viagem (
    id SERIAL PRIMARY KEY,
    embarcacao_id INTEGER NOT NULL,
    rota_id INTEGER NOT NULL,
    data_partida TIMESTAMP NOT NULL,
    FOREIGN KEY (embarcacao_id) REFERENCES embarcacao(id),
    FOREIGN KEY (rota_id) REFERENCES rota(id)
);

-- tipo: REDE ou CAMAROTE
CREATE TABLE passagem (
    id SERIAL PRIMARY KEY,
    viagem_id INTEGER NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    passageiro_nome VARCHAR(120) NOT NULL,
    passageiro_cpf VARCHAR(14) NOT NULL,
    passageiro_documento VARCHAR(30),
    valor NUMERIC(12,2) NOT NULL,
    FOREIGN KEY (viagem_id) REFERENCES viagem(id)
);
