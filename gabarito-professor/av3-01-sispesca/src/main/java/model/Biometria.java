package model;

import java.time.LocalDate;

/**
 * Registro periódico de peso e comprimento médio dos peixes de um lote.
 * Usado para calcular o ganho de peso e, junto com a ração consumida,
 * a taxa de conversão alimentar (TCA).
 */
public class Biometria {

    private LocalDate data;
    private double pesoMedioGramas;
    private double comprimentoMedioCm;

    public Biometria(LocalDate data, double pesoMedioGramas, double comprimentoMedioCm) {
        this.data = data;
        this.pesoMedioGramas = pesoMedioGramas;
        this.comprimentoMedioCm = comprimentoMedioCm;
    }

    public LocalDate getData() {
        return data;
    }

    public double getPesoMedioGramas() {
        return pesoMedioGramas;
    }

    public double getComprimentoMedioCm() {
        return comprimentoMedioCm;
    }

    @Override
    public String toString() {
        return data + " - Peso médio: " + pesoMedioGramas + "g - Comprimento: " + comprimentoMedioCm + "cm";
    }
}
