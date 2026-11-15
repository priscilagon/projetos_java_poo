-- ============================================================
-- SisPesca — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em cooperado
SELECT COUNT(*) AS total_cooperado FROM cooperado;

-- Amostra de cooperado
SELECT * FROM cooperado LIMIT 5;

-- Total de registros em criadouro
SELECT COUNT(*) AS total_criadouro FROM criadouro;

-- Amostra de criadouro
SELECT * FROM criadouro LIMIT 5;

-- Total de registros em lote_peixe
SELECT COUNT(*) AS total_lote_peixe FROM lote_peixe;

-- Amostra de lote_peixe
SELECT * FROM lote_peixe LIMIT 5;

-- Total de registros em biometria
SELECT COUNT(*) AS total_biometria FROM biometria;

-- Amostra de biometria
SELECT * FROM biometria LIMIT 5;

-- Exemplo de JOIN: lote_peixe com cooperado
SELECT t.*, r.*
FROM lote_peixe t
JOIN cooperado r ON t.cooperado_id = r.id;
