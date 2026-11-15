package model;

/**
 * Representa um viveiro escavado em terra (área em m²).
 * A capacidade segue densidade diferente da usada em tanque-rede,
 * evidenciando o polimorfismo entre as duas subclasses de Criadouro.
 */
public class Viveiro extends Criadouro {

    private double areaM2;
    private static final int DENSIDADE_PEIXES_POR_M2 = 4; // parâmetro técnico de referência

    public Viveiro(String codigo, String especieCriada, double areaM2) {
        super(codigo, especieCriada);
        this.areaM2 = areaM2;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    // Implementação polimórfica: regra específica de viveiro escavado
    @Override
    public int calcularCapacidadeMaxima() {
        return (int) Math.round(areaM2 * DENSIDADE_PEIXES_POR_M2);
    }
}
