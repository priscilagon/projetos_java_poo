package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Representa o ciclo de empréstimo/devolução de um exemplar por um aluno. */
public class Emprestimo {
    private Livro livro;
    private Aluno aluno;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao; // null enquanto não devolvido

    public Emprestimo(Livro livro, Aluno aluno) {
        this.livro = livro;
        this.aluno = aluno;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevista = dataEmprestimo.plusDays(livro.getPrazoEmprestimoDias());
    }

    public Livro getLivro() { return livro; }
    public Aluno getAluno() { return aluno; }
    public LocalDate getDataPrevista() { return dataPrevista; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public boolean isAtivo() { return dataDevolucao == null; }

    public boolean estaAtrasado() {
        return isAtivo() && LocalDate.now().isAfter(dataPrevista);
    }

    public double devolver() {
        this.dataDevolucao = LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataDevolucao);
        livro.devolverExemplar();
        return livro.getCalculadoraMulta().calcular(Math.max(diasAtraso, 0));
    }

    @Override
    public String toString() {
        String status = isAtivo() ? (estaAtrasado() ? "EM ATRASO" : "ativo") : "devolvido em " + dataDevolucao;
        return livro.getTitulo() + " -> " + aluno.getNome() + " (previsto: " + dataPrevista + ", status: " + status + ")";
    }
}
