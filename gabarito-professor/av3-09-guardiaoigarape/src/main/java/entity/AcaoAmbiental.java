package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "acao_ambiental".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class AcaoAmbiental {

    private int id;
    private String tipo;
    private String local;
    private LocalDate data;
    private double duracaoHoras;

    public AcaoAmbiental() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public AcaoAmbiental(String tipo, String local, LocalDate data, double duracaoHoras) {
        this.tipo = tipo;
        this.local = local;
        this.data = data;
        this.duracaoHoras = duracaoHoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(double duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public String toString() {
        return "AcaoAmbiental{id=" + id + "}";
    }
}
