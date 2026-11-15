-- ============================================================
-- FeiraDaPanair — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: banca
INSERT INTO banca (numero, localizacao) VALUES (12, 'Feira da Panair');

-- Tabela: feirante
INSERT INTO feirante (nome, cpf, banca_id) VALUES ('Maria das Graças', '111.222.333-44', 1);

-- Tabela: produto
INSERT INTO produto (nome, categoria, preco_unitario, estoque_disponivel) VALUES ('Açaí (litro)', 'FRUTA', 12.0, 100);
INSERT INTO produto (nome, categoria, preco_unitario, estoque_disponivel) VALUES ('Tambaqui (kg)', 'PESCADO', 22.0, 50);

-- Tabela: venda
INSERT INTO venda (produto_id, feirante_id, quantidade, data, valor_final) VALUES (1, 1, 20, '2026-07-20', 216.0);
