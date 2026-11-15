package model;

/** Aluno matriculado no CETAM, que pode realizar empréstimos. */
public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String turma;

    public Aluno(String nome, String matricula, String curso, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turma = turma;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getCurso() { return curso; }
    public String getTurma() { return turma; }

    @Override
    public String toString() {
        return nome + " (Mat. " + matricula + " - " + curso + "/" + turma + ")";
    }
}
