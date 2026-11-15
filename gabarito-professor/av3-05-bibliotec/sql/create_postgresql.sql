-- ============================================================
-- BiblioTec — Script de criação de tabelas (PostgreSQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS emprestimo CASCADE;
DROP TABLE IF EXISTS aluno CASCADE;
DROP TABLE IF EXISTS livro CASCADE;

-- categoria: tecnico, literatura ou referencia
CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    exemplares_total INTEGER NOT NULL,
    exemplares_disponiveis INTEGER NOT NULL,
    prazo_emprestimo_dias INTEGER NOT NULL
);

CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    curso VARCHAR(80),
    turma VARCHAR(30)
);

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    livro_id INTEGER NOT NULL,
    aluno_id INTEGER NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_prevista DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);
