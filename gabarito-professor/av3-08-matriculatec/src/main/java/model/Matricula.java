package model;

public class Matricula {
    private Aluno aluno;
    private Turma turma;
    private SituacaoMatricula situacao;

    public Matricula(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
        this.situacao = SituacaoMatricula.ATIVA;
    }

    public Aluno getAluno() { return aluno; }
    public Turma getTurma() { return turma; }
    public SituacaoMatricula getSituacao() { return situacao; }
    public void setSituacao(SituacaoMatricula situacao) { this.situacao = situacao; }

    @Override
    public String toString() {
        return aluno.getNome() + " - " + turma.getCodigo() + " - " + situacao;
    }
}
