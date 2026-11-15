package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "medicao".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Medicao {

    private int id;
    private int estacaoId;
    private LocalDate data;
    private double nivelMetros;

    public Medicao() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Medicao(int estacaoId, LocalDate data, double nivelMetros) {
        this.estacaoId = estacaoId;
        this.data = data;
        this.nivelMetros = nivelMetros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstacaoId() {
        return estacaoId;
    }

    public void setEstacaoId(int estacaoId) {
        this.estacaoId = estacaoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getNivelMetros() {
        return nivelMetros;
    }

    public void setNivelMetros(double nivelMetros) {
        this.nivelMetros = nivelMetros;
    }

    @Override
    public String toString() {
        return "Medicao{id=" + id + "}";
    }
}
