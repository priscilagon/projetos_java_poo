package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "lote_peixe".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class LotePeixe {

    private int id;
    private String identificador;
    private int cooperadoId;
    private int criadouroId;
    private LocalDate dataPovoamento;
    private int quantidadeAlevinos;
    private double pesoInicialMedioGramas;
    private double racaoTotalConsumidaKg;

    public LotePeixe() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public LotePeixe(String identificador, int cooperadoId, int criadouroId, LocalDate dataPovoamento, int quantidadeAlevinos, double pesoInicialMedioGramas, double racaoTotalConsumidaKg) {
        this.identificador = identificador;
        this.cooperadoId = cooperadoId;
        this.criadouroId = criadouroId;
        this.dataPovoamento = dataPovoamento;
        this.quantidadeAlevinos = quantidadeAlevinos;
        this.pesoInicialMedioGramas = pesoInicialMedioGramas;
        this.racaoTotalConsumidaKg = racaoTotalConsumidaKg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getCooperadoId() {
        return cooperadoId;
    }

    public void setCooperadoId(int cooperadoId) {
        this.cooperadoId = cooperadoId;
    }

    public int getCriadouroId() {
        return criadouroId;
    }

    public void setCriadouroId(int criadouroId) {
        this.criadouroId = criadouroId;
    }

    public LocalDate getDataPovoamento() {
        return dataPovoamento;
    }

    public void setDataPovoamento(LocalDate dataPovoamento) {
        this.dataPovoamento = dataPovoamento;
    }

    public int getQuantidadeAlevinos() {
        return quantidadeAlevinos;
    }

    public void setQuantidadeAlevinos(int quantidadeAlevinos) {
        this.quantidadeAlevinos = quantidadeAlevinos;
    }

    public double getPesoInicialMedioGramas() {
        return pesoInicialMedioGramas;
    }

    public void setPesoInicialMedioGramas(double pesoInicialMedioGramas) {
        this.pesoInicialMedioGramas = pesoInicialMedioGramas;
    }

    public double getRacaoTotalConsumidaKg() {
        return racaoTotalConsumidaKg;
    }

    public void setRacaoTotalConsumidaKg(double racaoTotalConsumidaKg) {
        this.racaoTotalConsumidaKg = racaoTotalConsumidaKg;
    }

    @Override
    public String toString() {
        return "LotePeixe{id=" + id + "}";
    }
}
