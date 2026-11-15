package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "aluno".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Aluno {

    private int id;
    private String nome;
    private String matricula;
    private LocalDate dataNascimento;

    public Aluno() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Aluno(String nome, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Aluno{id=" + id + "}";
    }
}
