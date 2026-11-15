package model;

/**
 * Classe abstrata que representa uma passagem vendida numa viagem fluvial.
 * HERANÇA/POLIMORFISMO: PassagemRede e PassagemCamarote calculam o preço
 * final de forma diferente a partir do preço base da rota.
 */
public abstract class Passagem {

    private Passageiro passageiro;
    private double precoBase;

    public Passagem(Passageiro passageiro, double precoBase) {
        this.passageiro = passageiro;
        this.precoBase = precoBase;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    /** Cada subtipo de passagem define seu próprio cálculo de valor final. */
    public abstract double calcularValor();

    @Override
    public String toString() {
        return String.format("%s - %s - R$ %.2f", getClass().getSimpleName(),
                passageiro.getNome(), calcularValor());
    }
}
