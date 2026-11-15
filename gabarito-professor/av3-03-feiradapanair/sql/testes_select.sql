-- ============================================================
-- FeiraDaPanair — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em banca
SELECT COUNT(*) AS total_banca FROM banca;

-- Amostra de banca
SELECT * FROM banca LIMIT 5;

-- Total de registros em feirante
SELECT COUNT(*) AS total_feirante FROM feirante;

-- Amostra de feirante
SELECT * FROM feirante LIMIT 5;

-- Total de registros em produto
SELECT COUNT(*) AS total_produto FROM produto;

-- Amostra de produto
SELECT * FROM produto LIMIT 5;

-- Total de registros em venda
SELECT COUNT(*) AS total_venda FROM venda;

-- Amostra de venda
SELECT * FROM venda LIMIT 5;

-- Exemplo de JOIN: feirante com banca
SELECT t.*, r.*
FROM feirante t
JOIN banca r ON t.banca_id = r.id;
