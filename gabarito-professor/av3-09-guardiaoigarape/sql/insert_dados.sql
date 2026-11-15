-- ============================================================
-- GuardiaoIgarape — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: voluntario
INSERT INTO voluntario (nome, contato) VALUES ('Beatriz Nogueira', 'beatriz@email.com');

-- Tabela: acao_ambiental
INSERT INTO acao_ambiental (tipo, local, data, duracao_horas) VALUES ('LIMPEZA', 'Igarapé do Quarenta', '2026-07-15', 4.0);

-- Tabela: participacao
INSERT INTO participacao (voluntario_id, acao_id) VALUES (1, 1);
