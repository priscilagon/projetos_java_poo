-- ============================================================
-- AgroCoop — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em produtor
SELECT COUNT(*) AS total_produtor FROM produtor;

-- Amostra de produtor
SELECT * FROM produtor LIMIT 5;

-- Total de registros em produto_agricola
SELECT COUNT(*) AS total_produto_agricola FROM produto_agricola;

-- Amostra de produto_agricola
SELECT * FROM produto_agricola LIMIT 5;

-- Total de registros em entrega
SELECT COUNT(*) AS total_entrega FROM entrega;

-- Amostra de entrega
SELECT * FROM entrega LIMIT 5;

-- Exemplo de JOIN: entrega com produtor
SELECT t.*, r.*
FROM entrega t
JOIN produtor r ON t.produtor_id = r.id;
