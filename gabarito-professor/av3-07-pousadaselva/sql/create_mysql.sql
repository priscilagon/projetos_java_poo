-- ============================================================
-- PousadaSelva — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS reserva_pacote;
DROP TABLE IF EXISTS reserva;
DROP TABLE IF EXISTS pacote_turistico;
DROP TABLE IF EXISTS hospede;
DROP TABLE IF EXISTS acomodacao;

-- tipo: QUARTO_PADRAO, BANGALO ou SUITE_FLUTUANTE
CREATE TABLE acomodacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identificacao VARCHAR(20) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL,
    valor_diaria DECIMAL(12,2) NOT NULL
);

-- tipo: NACIONAL ou ESTRANGEIRO
CREATE TABLE hospede (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    documento VARCHAR(30) NOT NULL UNIQUE,
    tipo VARCHAR(15) NOT NULL
);

CREATE TABLE pacote_turistico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250),
    valor DECIMAL(12,2) NOT NULL
);

CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acomodacao_id INT NOT NULL,
    hospede_id INT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    FOREIGN KEY (acomodacao_id) REFERENCES acomodacao(id),
    FOREIGN KEY (hospede_id) REFERENCES hospede(id)
);

CREATE TABLE reserva_pacote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reserva_id INT NOT NULL,
    pacote_id INT NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES reserva(id),
    FOREIGN KEY (pacote_id) REFERENCES pacote_turistico(id)
);
