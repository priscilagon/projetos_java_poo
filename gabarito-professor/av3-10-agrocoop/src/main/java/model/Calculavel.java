package model;

/** Interface que define uma forma de calcular a receita de uma entrega. */
public interface Calculavel {
    double calcularValor(double quantidade, double precoReferencia);
}
