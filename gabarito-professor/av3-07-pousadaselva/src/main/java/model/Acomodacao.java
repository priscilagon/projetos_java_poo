package model;

/**
 * Classe abstrata que representa uma acomodação da pousada.
 * HERANÇA/POLIMORFISMO: cada tipo define seu próprio valor de diária.
 */
public abstract class Acomodacao {
    private String identificacao;

    public Acomodacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getIdentificacao() { return identificacao; }

    public abstract double valorDiaria();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + identificacao + " - R$ " + String.format("%.2f", valorDiaria()) + "/diária";
    }
}
