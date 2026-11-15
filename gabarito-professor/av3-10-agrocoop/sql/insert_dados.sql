-- ============================================================
-- AgroCoop — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: produtor
INSERT INTO produtor (nome, comunidade, propriedade) VALUES ('Antônio Ribeiro', 'Comunidade São José', 'Sítio Ribeiro - Iranduba');

-- Tabela: produto_agricola
INSERT INTO produto_agricola (nome, unidade_medida, preco_referencia) VALUES ('Farinha de mandioca', 'saca', 90.0);

-- Tabela: entrega
INSERT INTO entrega (produtor_id, produto_id, quantidade, data, destino) VALUES (1, 1, 10, '2026-07-18', 'INSTITUCIONAL');
