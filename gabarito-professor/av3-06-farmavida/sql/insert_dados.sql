-- ============================================================
-- FarmaVida — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: medicamento
INSERT INTO medicamento (nome, principio_ativo, estoque_minimo) VALUES ('Dipirona 500mg', 'Dipirona Sódica', 20);

-- Tabela: lote
INSERT INTO lote (codigo, medicamento_id, quantidade, validade) VALUES ('L001', 1, 15, '2026-08-15');

-- Tabela: movimentacao_estoque
INSERT INTO movimentacao_estoque (lote_id, tipo, quantidade, data) VALUES (1, 'ENTRADA', 15, '2026-07-01');
