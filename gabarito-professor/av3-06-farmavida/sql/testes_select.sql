-- ============================================================
-- FarmaVida — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em medicamento
SELECT COUNT(*) AS total_medicamento FROM medicamento;

-- Amostra de medicamento
SELECT * FROM medicamento LIMIT 5;

-- Total de registros em lote
SELECT COUNT(*) AS total_lote FROM lote;

-- Amostra de lote
SELECT * FROM lote LIMIT 5;

-- Total de registros em movimentacao_estoque
SELECT COUNT(*) AS total_movimentacao_estoque FROM movimentacao_estoque;

-- Amostra de movimentacao_estoque
SELECT * FROM movimentacao_estoque LIMIT 5;

-- Exemplo de JOIN: lote com medicamento
SELECT t.*, r.*
FROM lote t
JOIN medicamento r ON t.medicamento_id = r.id;
