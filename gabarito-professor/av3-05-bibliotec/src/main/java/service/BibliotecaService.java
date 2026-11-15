package service;

import model.*;
import exception.AlunoComPendenciaException;

import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: acervo, alunos e ciclo de empréstimo/devolução. */
public class BibliotecaService {

    private List<Livro> acervo = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(Livro l) { acervo.add(l); }
    public void cadastrarAluno(Aluno a) { alunos.add(a); }

    public List<Livro> getAcervo() { return acervo; }
    public List<Aluno> getAlunos() { return alunos; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro l : acervo) if (l.getTitulo().equalsIgnoreCase(titulo)) return l;
        return null;
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno a : alunos) if (a.getMatricula().equalsIgnoreCase(matricula)) return a;
        return null;
    }

    private boolean possuiPendencia(Aluno aluno) {
        for (Emprestimo e : emprestimos) {
            if (e.getAluno() == aluno && e.estaAtrasado()) return true;
        }
        return false;
    }

    /** Realiza o empréstimo, validando disponibilidade e pendências do aluno. */
    public void realizarEmprestimo(Livro livro, Aluno aluno) throws AlunoComPendenciaException {
        if (possuiPendencia(aluno)) {
            throw new AlunoComPendenciaException(
                    "Aluno " + aluno.getNome() + " possui empréstimo em atraso e não pode retirar novos livros.");
        }
        if (livro.getExemplaresDisponiveis() <= 0) {
            throw new AlunoComPendenciaException("Não há exemplares disponíveis de \"" + livro.getTitulo() + "\".");
        }
        livro.reservarExemplar();
        emprestimos.add(new Emprestimo(livro, aluno));
    }

    public Emprestimo buscarEmprestimoAtivo(Livro livro, Aluno aluno) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro() == livro && e.getAluno() == aluno && e.isAtivo()) return e;
        }
        return null;
    }

    public void imprimirRelatorioAtrasos() {
        System.out.println("\n===== Alunos com empréstimos em atraso =====");
        boolean algum = false;
        for (Emprestimo e : emprestimos) {
            if (e.estaAtrasado()) {
                System.out.println("- " + e);
                algum = true;
            }
        }
        if (!algum) System.out.println("Nenhum atraso no momento.");
    }
}
