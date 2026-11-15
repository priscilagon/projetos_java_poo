package model;

public class Voluntario {
    private String nome;
    private String contato;

    public Voluntario(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() { return nome; }
    public String getContato() { return contato; }

    @Override
    public String toString() {
        return nome + " (" + contato + ")";
    }
}
