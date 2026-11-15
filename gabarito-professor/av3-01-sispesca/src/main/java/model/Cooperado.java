package model;

/**
 * Representa o piscicultor cooperado, responsável por um ou mais criadouros.
 */
public class Cooperado {

    private String nome;
    private String cpf;
    private String propriedade;

    public Cooperado(String nome, String cpf, String propriedade) {
        this.nome = nome;
        this.cpf = cpf;
        this.propriedade = propriedade;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPropriedade() {
        return propriedade;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ") - Propriedade: " + propriedade;
    }
}
