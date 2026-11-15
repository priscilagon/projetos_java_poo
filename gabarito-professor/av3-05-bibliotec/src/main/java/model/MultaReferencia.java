package model;

/** Multa para obras de referência (alta demanda): R$ 1,20 por dia de atraso. */
public class MultaReferencia implements CalculadoraMulta {
    private static final double VALOR_POR_DIA = 1.20;

    @Override
    public double calcular(long diasAtraso) {
        return diasAtraso > 0 ? diasAtraso * VALOR_POR_DIA : 0.0;
    }
}
