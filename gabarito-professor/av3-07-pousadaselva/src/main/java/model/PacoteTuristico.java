package model;

public class PacoteTuristico {
    private String nome;
    private String descricao;
    private double valor;

    public PacoteTuristico(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", valor);
    }
}
