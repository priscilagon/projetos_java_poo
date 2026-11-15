-- ============================================================
-- MonitoraRioNegro — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS medicao;
DROP TABLE IF EXISTS estacao_monitoramento;

CREATE TABLE estacao_monitoramento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE,
    municipio VARCHAR(80) NOT NULL
);

CREATE TABLE medicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estacao_id INT NOT NULL,
    data DATE NOT NULL,
    nivel_metros DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (estacao_id) REFERENCES estacao_monitoramento(id)
);
