package model;

/** Representa a embarcação (barco regional ou lancha) com suas capacidades. */
public class Embarcacao {
    private String nome;
    private int capacidadeRede;
    private int capacidadeCamarote;

    public Embarcacao(String nome, int capacidadeRede, int capacidadeCamarote) {
        this.nome = nome;
        this.capacidadeRede = capacidadeRede;
        this.capacidadeCamarote = capacidadeCamarote;
    }

    public String getNome() { return nome; }
    public int getCapacidadeRede() { return capacidadeRede; }
    public int getCapacidadeCamarote() { return capacidadeCamarote; }

    @Override
    public String toString() {
        return nome + " (Redes: " + capacidadeRede + " | Camarotes: " + capacidadeCamarote + ")";
    }
}
