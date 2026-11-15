package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "biometria".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Biometria {

    private int id;
    private int loteId;
    private LocalDate data;
    private double pesoMedioGramas;
    private double comprimentoMedioCm;

    public Biometria() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Biometria(int loteId, LocalDate data, double pesoMedioGramas, double comprimentoMedioCm) {
        this.loteId = loteId;
        this.data = data;
        this.pesoMedioGramas = pesoMedioGramas;
        this.comprimentoMedioCm = comprimentoMedioCm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoteId() {
        return loteId;
    }

    public void setLoteId(int loteId) {
        this.loteId = loteId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getPesoMedioGramas() {
        return pesoMedioGramas;
    }

    public void setPesoMedioGramas(double pesoMedioGramas) {
        this.pesoMedioGramas = pesoMedioGramas;
    }

    public double getComprimentoMedioCm() {
        return comprimentoMedioCm;
    }

    public void setComprimentoMedioCm(double comprimentoMedioCm) {
        this.comprimentoMedioCm = comprimentoMedioCm;
    }

    @Override
    public String toString() {
        return "Biometria{id=" + id + "}";
    }
}
