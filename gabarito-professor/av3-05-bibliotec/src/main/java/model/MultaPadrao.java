package model;

/** Multa padrão: R$ 0,50 por dia de atraso, para qualquer categoria de livro. */
public class MultaPadrao implements CalculadoraMulta {
    private static final double VALOR_POR_DIA = 0.50;

    @Override
    public double calcular(long diasAtraso) {
        return diasAtraso > 0 ? diasAtraso * VALOR_POR_DIA : 0.0;
    }
}
