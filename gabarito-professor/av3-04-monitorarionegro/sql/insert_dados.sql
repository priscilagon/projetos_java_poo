-- ============================================================
-- MonitoraRioNegro — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: estacao_monitoramento
INSERT INTO estacao_monitoramento (nome, municipio) VALUES ('Porto de Manaus', 'Manaus');
INSERT INTO estacao_monitoramento (nome, municipio) VALUES ('Igarapé do Mindu', 'Manaus');

-- Tabela: medicao
INSERT INTO medicao (estacao_id, data, nivel_metros) VALUES (1, '2026-07-20', 21.5);
