-- ============================================================
-- PousadaSelva — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em acomodacao
SELECT COUNT(*) AS total_acomodacao FROM acomodacao;

-- Amostra de acomodacao
SELECT * FROM acomodacao LIMIT 5;

-- Total de registros em hospede
SELECT COUNT(*) AS total_hospede FROM hospede;

-- Amostra de hospede
SELECT * FROM hospede LIMIT 5;

-- Total de registros em pacote_turistico
SELECT COUNT(*) AS total_pacote_turistico FROM pacote_turistico;

-- Amostra de pacote_turistico
SELECT * FROM pacote_turistico LIMIT 5;

-- Total de registros em reserva
SELECT COUNT(*) AS total_reserva FROM reserva;

-- Amostra de reserva
SELECT * FROM reserva LIMIT 5;

-- Total de registros em reserva_pacote
SELECT COUNT(*) AS total_reserva_pacote FROM reserva_pacote;

-- Amostra de reserva_pacote
SELECT * FROM reserva_pacote LIMIT 5;

-- Exemplo de JOIN: reserva com acomodacao
SELECT t.*, r.*
FROM reserva t
JOIN acomodacao r ON t.acomodacao_id = r.id;
