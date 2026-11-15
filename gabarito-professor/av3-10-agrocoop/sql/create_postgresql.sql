-- ============================================================
-- AgroCoop — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS entrega CASCADE;
DROP TABLE IF EXISTS produto_agricola CASCADE;
DROP TABLE IF EXISTS produtor CASCADE;

CREATE TABLE produtor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    comunidade VARCHAR(100),
    propriedade VARCHAR(150)
);

-- unidade_medida: kg, saca ou litro
CREATE TABLE produto_agricola (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(10) NOT NULL,
    preco_referencia NUMERIC(12,2) NOT NULL
);

-- destino: MERCADO_COMUM ou INSTITUCIONAL
CREATE TABLE entrega (
    id SERIAL PRIMARY KEY,
    produtor_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    quantidade NUMERIC(12,2) NOT NULL,
    data DATE NOT NULL,
    destino VARCHAR(20) NOT NULL DEFAULT 'MERCADO_COMUM',
    FOREIGN KEY (produtor_id) REFERENCES produtor(id),
    FOREIGN KEY (produto_id) REFERENCES produto_agricola(id)
);
