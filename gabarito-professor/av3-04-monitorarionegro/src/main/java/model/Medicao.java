package model;

import java.time.LocalDate;

/** Registro diário de nível (em metros) medido em uma estação. */
public class Medicao {
    private LocalDate data;
    private double nivelMetros;

    public Medicao(LocalDate data, double nivelMetros) {
        this.data = data;
        this.nivelMetros = nivelMetros;
    }

    public LocalDate getData() { return data; }
    public double getNivelMetros() { return nivelMetros; }

    @Override
    public String toString() {
        return data + " - " + nivelMetros + "m";
    }
}
