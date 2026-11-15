package model;

/**
 * Implementação baseada nas faixas históricas de referência do Porto de Manaus
 * (cota do Rio Negro em metros).
 */
public class ClassificadorPortoDeManaus implements ClassificadorNivel {

    @Override
    public String classificar(double nivelMetros) {
        if (nivelMetros >= 29.0) return "CHEIA EXTREMA";
        if (nivelMetros >= 27.0) return "CHEIA";
        if (nivelMetros <= 15.0) return "SECA EXTREMA";
        if (nivelMetros <= 18.0) return "SECA";
        return "NÍVEL NORMAL";
    }
}
