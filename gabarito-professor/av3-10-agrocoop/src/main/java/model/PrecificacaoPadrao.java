package model;

/** Precificação padrão: quantidade × preço de referência, sem ajustes. */
public class PrecificacaoPadrao implements Calculavel {
    @Override
    public double calcularValor(double quantidade, double precoReferencia) {
        return quantidade * precoReferencia;
    }
}
