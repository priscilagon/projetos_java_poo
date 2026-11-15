-- ============================================================
-- PousadaSelva — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: acomodacao
INSERT INTO acomodacao (identificacao, tipo, valor_diaria) VALUES ('BG01', 'BANGALO', 320.0);

-- Tabela: hospede
INSERT INTO hospede (nome, documento, tipo) VALUES ('John Smith', 'PASS123456', 'ESTRANGEIRO');

-- Tabela: pacote_turistico
INSERT INTO pacote_turistico (nome, descricao, valor) VALUES ('Encontro das Águas', 'Passeio de barco ao encontro dos rios Negro e Solimões', 150.0);

-- Tabela: reserva
INSERT INTO reserva (acomodacao_id, hospede_id, check_in, check_out) VALUES (1, 1, '2026-08-10', '2026-08-13');

-- Tabela: reserva_pacote
INSERT INTO reserva_pacote (reserva_id, pacote_id) VALUES (1, 1);
