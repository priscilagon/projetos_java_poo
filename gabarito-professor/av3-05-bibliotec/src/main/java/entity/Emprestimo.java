package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "emprestimo".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Emprestimo {

    private int id;
    private int livroId;
    private int alunoId;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;

    public Emprestimo() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Emprestimo(int livroId, int alunoId, LocalDate dataEmprestimo, LocalDate dataPrevista, LocalDate dataDevolucao) {
        this.livroId = livroId;
        this.alunoId = alunoId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{id=" + id + "}";
    }
}
