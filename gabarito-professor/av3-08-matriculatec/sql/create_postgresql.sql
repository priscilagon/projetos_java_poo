-- ============================================================
-- MatriculaTec — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS matricula CASCADE;
DROP TABLE IF EXISTS aluno CASCADE;
DROP TABLE IF EXISTS turma CASCADE;
DROP TABLE IF EXISTS disciplina CASCADE;
DROP TABLE IF EXISTS modulo CASCADE;
DROP TABLE IF EXISTS curso CASCADE;

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INTEGER NOT NULL
);

CREATE TABLE modulo (
    id SERIAL PRIMARY KEY,
    curso_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    modulo_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INTEGER NOT NULL,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    modulo_id INTEGER NOT NULL,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    limite_vagas INTEGER NOT NULL,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    data_nascimento DATE
);

-- situacao: ATIVA, TRANCADA, CONCLUIDA ou CANCELADA
CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    aluno_id INTEGER NOT NULL,
    turma_id INTEGER NOT NULL,
    situacao VARCHAR(15) NOT NULL DEFAULT 'ATIVA',
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id)
);
