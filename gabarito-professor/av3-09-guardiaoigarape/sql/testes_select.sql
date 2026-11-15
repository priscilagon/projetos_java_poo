-- ============================================================
-- GuardiaoIgarape — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em voluntario
SELECT COUNT(*) AS total_voluntario FROM voluntario;

-- Amostra de voluntario
SELECT * FROM voluntario LIMIT 5;

-- Total de registros em acao_ambiental
SELECT COUNT(*) AS total_acao_ambiental FROM acao_ambiental;

-- Amostra de acao_ambiental
SELECT * FROM acao_ambiental LIMIT 5;

-- Total de registros em participacao
SELECT COUNT(*) AS total_participacao FROM participacao;

-- Amostra de participacao
SELECT * FROM participacao LIMIT 5;

-- Exemplo de JOIN: participacao com voluntario
SELECT t.*, r.*
FROM participacao t
JOIN voluntario r ON t.voluntario_id = r.id;
