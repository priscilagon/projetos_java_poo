-- ============================================================
-- BiblioTec — Script de criação de tabelas (MySQL)
-- Gerado a partir do gabarito oficial dos 10 Projetos Java POO (AV3) — CETAM
-- ============================================================

DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS livro;

-- categoria: tecnico, literatura ou referencia
CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    exemplares_total INT NOT NULL,
    exemplares_disponiveis INT NOT NULL,
    prazo_emprestimo_dias INT NOT NULL
);

CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    curso VARCHAR(80),
    turma VARCHAR(30)
);

CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    aluno_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_prevista DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);
