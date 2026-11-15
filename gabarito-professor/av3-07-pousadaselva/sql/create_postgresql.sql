-- ============================================================
-- PousadaSelva — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS reserva_pacote CASCADE;
DROP TABLE IF EXISTS reserva CASCADE;
DROP TABLE IF EXISTS pacote_turistico CASCADE;
DROP TABLE IF EXISTS hospede CASCADE;
DROP TABLE IF EXISTS acomodacao CASCADE;

-- tipo: QUARTO_PADRAO, BANGALO ou SUITE_FLUTUANTE
CREATE TABLE acomodacao (
    id SERIAL PRIMARY KEY,
    identificacao VARCHAR(20) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL,
    valor_diaria NUMERIC(12,2) NOT NULL
);

-- tipo: NACIONAL ou ESTRANGEIRO
CREATE TABLE hospede (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    documento VARCHAR(30) NOT NULL UNIQUE,
    tipo VARCHAR(15) NOT NULL
);

CREATE TABLE pacote_turistico (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250),
    valor NUMERIC(12,2) NOT NULL
);

CREATE TABLE reserva (
    id SERIAL PRIMARY KEY,
    acomodacao_id INTEGER NOT NULL,
    hospede_id INTEGER NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    FOREIGN KEY (acomodacao_id) REFERENCES acomodacao(id),
    FOREIGN KEY (hospede_id) REFERENCES hospede(id)
);

CREATE TABLE reserva_pacote (
    id SERIAL PRIMARY KEY,
    reserva_id INTEGER NOT NULL,
    pacote_id INTEGER NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES reserva(id),
    FOREIGN KEY (pacote_id) REFERENCES pacote_turistico(id)
);
