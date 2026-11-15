package model;

/**
 * Interface que define a estratégia de cálculo de multa por atraso.
 * POLIMORFISMO: cada categoria de livro pode ter sua própria implementação.
 */
public interface CalculadoraMulta {
    double calcular(long diasAtraso);
}
