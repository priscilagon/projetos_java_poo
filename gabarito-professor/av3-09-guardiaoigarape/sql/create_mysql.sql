-- ============================================================
-- GuardiaoIgarape — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS participacao;
DROP TABLE IF EXISTS acao_ambiental;
DROP TABLE IF EXISTS voluntario;

CREATE TABLE voluntario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    contato VARCHAR(100)
);

-- tipo: LIMPEZA, PLANTIO ou EDUCACAO_AMBIENTAL
CREATE TABLE acao_ambiental (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(25) NOT NULL,
    local VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    duracao_horas DECIMAL(12,2) NOT NULL
);

CREATE TABLE participacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    voluntario_id INT NOT NULL,
    acao_id INT NOT NULL,
    FOREIGN KEY (voluntario_id) REFERENCES voluntario(id),
    FOREIGN KEY (acao_id) REFERENCES acao_ambiental(id)
);
