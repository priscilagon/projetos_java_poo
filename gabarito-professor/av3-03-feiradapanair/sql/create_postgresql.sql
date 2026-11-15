-- ============================================================
-- FeiraDaPanair — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS venda CASCADE;
DROP TABLE IF EXISTS produto CASCADE;
DROP TABLE IF EXISTS feirante CASCADE;
DROP TABLE IF EXISTS banca CASCADE;

CREATE TABLE banca (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL UNIQUE,
    localizacao VARCHAR(100)
);

CREATE TABLE feirante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    banca_id INTEGER NOT NULL,
    FOREIGN KEY (banca_id) REFERENCES banca(id)
);

-- categoria: FRUTA, PESCADO ou ARTESANATO
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    preco_unitario NUMERIC(12,2) NOT NULL,
    estoque_disponivel INTEGER NOT NULL
);

CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL,
    feirante_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    data DATE NOT NULL,
    valor_final NUMERIC(12,2) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (feirante_id) REFERENCES feirante(id)
);
