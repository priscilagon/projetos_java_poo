-- ============================================================
-- FeiraDaPanair — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS feirante;
DROP TABLE IF EXISTS banca;

CREATE TABLE banca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE,
    localizacao VARCHAR(100)
);

CREATE TABLE feirante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    banca_id INT NOT NULL,
    FOREIGN KEY (banca_id) REFERENCES banca(id)
);

-- categoria: FRUTA, PESCADO ou ARTESANATO
CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    preco_unitario DECIMAL(12,2) NOT NULL,
    estoque_disponivel INT NOT NULL
);

CREATE TABLE venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    feirante_id INT NOT NULL,
    quantidade INT NOT NULL,
    data DATE NOT NULL,
    valor_final DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (feirante_id) REFERENCES feirante(id)
);
