-- ============================================================
-- NaveFluvial — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em embarcacao
SELECT COUNT(*) AS total_embarcacao FROM embarcacao;

-- Amostra de embarcacao
SELECT * FROM embarcacao LIMIT 5;

-- Total de registros em rota
SELECT COUNT(*) AS total_rota FROM rota;

-- Amostra de rota
SELECT * FROM rota LIMIT 5;

-- Total de registros em viagem
SELECT COUNT(*) AS total_viagem FROM viagem;

-- Amostra de viagem
SELECT * FROM viagem LIMIT 5;

-- Total de registros em passagem
SELECT COUNT(*) AS total_passagem FROM passagem;

-- Amostra de passagem
SELECT * FROM passagem LIMIT 5;

-- Exemplo de JOIN: viagem com embarcacao
SELECT t.*, r.*
FROM viagem t
JOIN embarcacao r ON t.embarcacao_id = r.id;
