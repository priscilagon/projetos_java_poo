-- ============================================================
-- SisPesca — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS biometria;
DROP TABLE IF EXISTS lote_peixe;
DROP TABLE IF EXISTS criadouro;
DROP TABLE IF EXISTS cooperado;

CREATE TABLE cooperado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    propriedade VARCHAR(150)
);

-- tipo: TANQUE_REDE ou VIVEIRO
CREATE TABLE criadouro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL,
    especie_criada VARCHAR(60) NOT NULL,
    volume_m3 DECIMAL(12,2),
    area_m2 DECIMAL(12,2)
);

CREATE TABLE lote_peixe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identificador VARCHAR(30) NOT NULL UNIQUE,
    cooperado_id INT NOT NULL,
    criadouro_id INT NOT NULL,
    data_povoamento DATE NOT NULL,
    quantidade_alevinos INT NOT NULL,
    peso_inicial_medio_gramas DECIMAL(12,2) NOT NULL,
    racao_total_consumida_kg DECIMAL(12,2) NOT NULL DEFAULT 0,
    FOREIGN KEY (cooperado_id) REFERENCES cooperado(id),
    FOREIGN KEY (criadouro_id) REFERENCES criadouro(id)
);

CREATE TABLE biometria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lote_id INT NOT NULL,
    data DATE NOT NULL,
    peso_medio_gramas DECIMAL(12,2) NOT NULL,
    comprimento_medio_cm DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (lote_id) REFERENCES lote_peixe(id)
);
