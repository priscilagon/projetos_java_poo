-- ============================================================
-- MatriculaTec — Dados de exemplo (compatível com PostgreSQL e MySQL)
-- Execute após o script de criação de tabelas.
-- ============================================================

-- Tabela: curso
INSERT INTO curso (nome, carga_horaria) VALUES ('Técnico em Informática', 1200);

-- Tabela: modulo
INSERT INTO modulo (curso_id, nome) VALUES (1, 'Módulo I - Fundamentos');

-- Tabela: disciplina
INSERT INTO disciplina (modulo_id, nome, carga_horaria) VALUES (1, 'Lógica de Programação', 80);
INSERT INTO disciplina (modulo_id, nome, carga_horaria) VALUES (1, 'Java POO', 80);

-- Tabela: turma
INSERT INTO turma (modulo_id, codigo, limite_vagas) VALUES (1, 'TI-2026-1', 30);

-- Tabela: aluno
INSERT INTO aluno (nome, matricula, data_nascimento) VALUES ('Carlos Lima', '2026010', '2008-03-15');

-- Tabela: matricula
INSERT INTO matricula (aluno_id, turma_id, situacao) VALUES (1, 1, 'ATIVA');
