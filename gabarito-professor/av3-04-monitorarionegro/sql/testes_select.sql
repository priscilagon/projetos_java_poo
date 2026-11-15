-- ============================================================
-- MonitoraRioNegro — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em estacao_monitoramento
SELECT COUNT(*) AS total_estacao_monitoramento FROM estacao_monitoramento;

-- Amostra de estacao_monitoramento
SELECT * FROM estacao_monitoramento LIMIT 5;

-- Total de registros em medicao
SELECT COUNT(*) AS total_medicao FROM medicao;

-- Amostra de medicao
SELECT * FROM medicao LIMIT 5;

-- Exemplo de JOIN: medicao com estacao_monitoramento
SELECT t.*, r.*
FROM medicao t
JOIN estacao_monitoramento r ON t.estacao_id = r.id;
