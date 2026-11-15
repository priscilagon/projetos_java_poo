package model;

/**
 * Classe abstrata que representa uma ação ambiental voluntária.
 * HERANÇA/POLIMORFISMO: cada subtipo define um peso diferente de
 * "impacto" usado no relatório (ex.: horas convertidas em pontos de impacto).
 */
public abstract class AcaoAmbiental {
    private String local; // ex.: Igarapé do Quarenta
    private java.time.LocalDate data;
    private double duracaoHoras;

    public AcaoAmbiental(String local, java.time.LocalDate data, double duracaoHoras) {
        this.local = local;
        this.data = data;
        this.duracaoHoras = duracaoHoras;
    }

    public String getLocal() { return local; }
    public java.time.LocalDate getData() { return data; }
    public double getDuracaoHoras() { return duracaoHoras; }

    /** Fator de impacto multiplicado pela duração para gerar a pontuação da ação. */
    public abstract double fatorImpacto();

    public double calcularPontuacaoImpacto() {
        return duracaoHoras * fatorImpacto();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " em " + local + " (" + data + ") - " + duracaoHoras + "h";
    }
}
