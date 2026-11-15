-- ============================================================
-- NaveFluvial — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS passagem;
DROP TABLE IF EXISTS viagem;
DROP TABLE IF EXISTS rota;
DROP TABLE IF EXISTS embarcacao;

CREATE TABLE embarcacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE,
    capacidade_rede INT NOT NULL,
    capacidade_camarote INT NOT NULL
);

CREATE TABLE rota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    origem VARCHAR(60) NOT NULL,
    destino VARCHAR(60) NOT NULL,
    distancia_km DECIMAL(12,2) NOT NULL,
    preco_base_passagem DECIMAL(12,2) NOT NULL
);

CREATE TABLE viagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    embarcacao_id INT NOT NULL,
    rota_id INT NOT NULL,
    data_partida TIMESTAMP NOT NULL,
    FOREIGN KEY (embarcacao_id) REFERENCES embarcacao(id),
    FOREIGN KEY (rota_id) REFERENCES rota(id)
);

-- tipo: REDE ou CAMAROTE
CREATE TABLE passagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    viagem_id INT NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    passageiro_nome VARCHAR(120) NOT NULL,
    passageiro_cpf VARCHAR(14) NOT NULL,
    passageiro_documento VARCHAR(30),
    valor DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (viagem_id) REFERENCES viagem(id)
);
