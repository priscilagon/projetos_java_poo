package model;

/**
 * Interface funcional que define a estratégia de classificação do nível do rio.
 * INTERFACE/POLIMORFISMO: diferentes implementações podem definir faixas
 * distintas (ex.: régua do Porto de Manaus x régua de um afluente).
 */
public interface ClassificadorNivel {
    String classificar(double nivelMetros);
}
