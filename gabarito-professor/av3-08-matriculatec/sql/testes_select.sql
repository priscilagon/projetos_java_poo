-- ============================================================
-- MatriculaTec — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em curso
SELECT COUNT(*) AS total_curso FROM curso;

-- Amostra de curso
SELECT * FROM curso LIMIT 5;

-- Total de registros em modulo
SELECT COUNT(*) AS total_modulo FROM modulo;

-- Amostra de modulo
SELECT * FROM modulo LIMIT 5;

-- Total de registros em disciplina
SELECT COUNT(*) AS total_disciplina FROM disciplina;

-- Amostra de disciplina
SELECT * FROM disciplina LIMIT 5;

-- Total de registros em turma
SELECT COUNT(*) AS total_turma FROM turma;

-- Amostra de turma
SELECT * FROM turma LIMIT 5;

-- Total de registros em aluno
SELECT COUNT(*) AS total_aluno FROM aluno;

-- Amostra de aluno
SELECT * FROM aluno LIMIT 5;

-- Total de registros em matricula
SELECT COUNT(*) AS total_matricula FROM matricula;

-- Amostra de matricula
SELECT * FROM matricula LIMIT 5;

-- Exemplo de JOIN: modulo com curso
SELECT t.*, r.*
FROM modulo t
JOIN curso r ON t.curso_id = r.id;
