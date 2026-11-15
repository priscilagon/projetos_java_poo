-- ============================================================
-- MatriculaTec — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS matricula;
DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS turma;
DROP TABLE IF EXISTS disciplina;
DROP TABLE IF EXISTS modulo;
DROP TABLE IF EXISTS curso;

CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL
);

CREATE TABLE modulo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modulo_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE turma (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modulo_id INT NOT NULL,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    limite_vagas INT NOT NULL,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    data_nascimento DATE
);

-- situacao: ATIVA, TRANCADA, CONCLUIDA ou CANCELADA
CREATE TABLE matricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    turma_id INT NOT NULL,
    situacao VARCHAR(15) NOT NULL DEFAULT 'ATIVA',
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id)
);
