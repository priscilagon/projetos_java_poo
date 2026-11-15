package service;

import model.*;
import exception.VagaIndisponivelException;

import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: cursos, turmas, alunos e o ciclo de matrícula. */
public class MatriculaService {

    private List<Curso> cursos = new ArrayList<>();
    private List<Turma> turmas = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();
    private List<Matricula> matriculas = new ArrayList<>();

    public void cadastrarCurso(Curso c) { cursos.add(c); }
    public void cadastrarTurma(Turma t) { turmas.add(t); }
    public void cadastrarAluno(Aluno a) { alunos.add(a); }

    public List<Curso> getCursos() { return cursos; }
    public List<Turma> getTurmas() { return turmas; }
    public List<Aluno> getAlunos() { return alunos; }
    public List<Matricula> getMatriculas() { return matriculas; }

    public Turma buscarTurmaPorCodigo(String codigo) {
        for (Turma t : turmas) if (t.getCodigo().equalsIgnoreCase(codigo)) return t;
        return null;
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno a : alunos) if (a.getMatricula().equalsIgnoreCase(matricula)) return a;
        return null;
    }

    /** Matricula um aluno em uma turma, respeitando o limite de vagas (regra de negócio). */
    public Matricula matricularAluno(Aluno aluno, Turma turma) throws VagaIndisponivelException {
        if (!turma.temVagaDisponivel()) {
            throw new VagaIndisponivelException("Turma " + turma.getCodigo() + " não possui vagas disponíveis.");
        }
        turma.getAlunosMatriculados().add(aluno);
        Matricula matricula = new Matricula(aluno, turma);
        matriculas.add(matricula);
        return matricula;
    }

    public void alterarSituacao(Matricula matricula, SituacaoMatricula situacao) {
        matricula.setSituacao(situacao);
    }

    public void imprimirRelatorioOcupacao() {
        System.out.println("\n===== Ocupação por turma =====");
        for (Turma t : turmas) {
            System.out.printf("%s -> %.1f%% de ocupação%n", t.getCodigo(), t.calcularTaxaOcupacao());
        }
    }
}
