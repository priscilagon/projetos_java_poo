-- ============================================================
-- SisPesca — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: cooperado
INSERT INTO cooperado (nome, cpf, propriedade) VALUES ('João Batista', '123.456.789-00', 'Comunidade Nova Esperança - Rio Preto da Eva');
INSERT INTO cooperado (nome, cpf, propriedade) VALUES ('Maria das Graças', '987.654.321-00', 'Sítio Boa Vista - Manacapuru');

-- Tabela: criadouro
INSERT INTO criadouro (codigo, tipo, especie_criada, volume_m3, area_m2) VALUES ('TR-01', 'TANQUE_REDE', 'Tambaqui', 20.0, NULL);
INSERT INTO criadouro (codigo, tipo, especie_criada, volume_m3, area_m2) VALUES ('VV-01', 'VIVEIRO', 'Pirarucu', NULL, 300.0);

-- Tabela: lote_peixe
INSERT INTO lote_peixe (identificador, cooperado_id, criadouro_id, data_povoamento, quantidade_alevinos, peso_inicial_medio_gramas, racao_total_consumida_kg) VALUES ('LOTE-2026-01', 1, 1, '2026-05-01', 500, 15.0, 120.0);

-- Tabela: biometria
INSERT INTO biometria (lote_id, data, peso_medio_gramas, comprimento_medio_cm) VALUES (1, '2026-07-01', 450.0, 22.0);
