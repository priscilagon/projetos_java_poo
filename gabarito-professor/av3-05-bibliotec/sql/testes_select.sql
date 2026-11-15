-- ============================================================
-- BiblioTec — Consultas de teste (validação dos dados inseridos)
-- ============================================================

-- Total de registros em livro
SELECT COUNT(*) AS total_livro FROM livro;

-- Amostra de livro
SELECT * FROM livro LIMIT 5;

-- Total de registros em aluno
SELECT COUNT(*) AS total_aluno FROM aluno;

-- Amostra de aluno
SELECT * FROM aluno LIMIT 5;

-- Total de registros em emprestimo
SELECT COUNT(*) AS total_emprestimo FROM emprestimo;

-- Amostra de emprestimo
SELECT * FROM emprestimo LIMIT 5;

-- Exemplo de JOIN: emprestimo com livro
SELECT t.*, r.*
FROM emprestimo t
JOIN livro r ON t.livro_id = r.id;
