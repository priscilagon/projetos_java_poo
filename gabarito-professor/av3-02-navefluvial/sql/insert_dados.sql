-- ============================================================
-- NaveFluvial — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: embarcacao
INSERT INTO embarcacao (nome, capacidade_rede, capacidade_camarote) VALUES ('Almirante Amazonas', 40, 10);

-- Tabela: rota
INSERT INTO rota (origem, destino, distancia_km, preco_base_passagem) VALUES ('Manaus', 'Parintins', 370, 150.0);

-- Tabela: viagem
INSERT INTO viagem (embarcacao_id, rota_id, data_partida) VALUES (1, 1, '2026-08-01 14:00:00');

-- Tabela: passagem
INSERT INTO passagem (viagem_id, tipo, passageiro_nome, passageiro_cpf, passageiro_documento, valor) VALUES (1, 'REDE', 'Kauã Ferreira', '111.222.333-44', 'RG-1234567', 150.0);
