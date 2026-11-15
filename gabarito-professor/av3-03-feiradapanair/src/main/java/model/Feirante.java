package model;

/** Feirante cadastrado, associado a uma banca. */
public class Feirante {
    private String nome;
    private String cpf;
    private Banca banca;

    public Feirante(String nome, String cpf, Banca banca) {
        this.nome = nome;
        this.cpf = cpf;
        this.banca = banca;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public Banca getBanca() { return banca; }

    @Override
    public String toString() {
        return nome + " - " + banca;
    }
}
