-- ============================================================
-- BiblioTec — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: livro
INSERT INTO livro (titulo, autor, categoria, exemplares_total, exemplares_disponiveis, prazo_emprestimo_dias) VALUES ('Java para Iniciantes', 'Autor Regional', 'tecnico', 3, 3, 7);

-- Tabela: aluno
INSERT INTO aluno (nome, matricula, curso, turma) VALUES ('Ana Souza', '2026001', 'Informática', 'Turma A');

-- Tabela: emprestimo
INSERT INTO emprestimo (livro_id, aluno_id, data_emprestimo, data_prevista, data_devolucao) VALUES (1, 1, '2026-07-10', '2026-07-17', NULL);
