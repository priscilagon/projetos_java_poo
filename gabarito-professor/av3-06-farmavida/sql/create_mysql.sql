-- ============================================================
-- FarmaVida — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS movimentacao_estoque;
DROP TABLE IF EXISTS lote;
DROP TABLE IF EXISTS medicamento;

CREATE TABLE medicamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    principio_ativo VARCHAR(100),
    estoque_minimo INT NOT NULL
);

CREATE TABLE lote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    medicamento_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    validade DATE NOT NULL,
    FOREIGN KEY (medicamento_id) REFERENCES medicamento(id)
);

-- tipo: ENTRADA ou SAIDA
CREATE TABLE movimentacao_estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lote_id INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    quantidade INT NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (lote_id) REFERENCES lote(id)
);
