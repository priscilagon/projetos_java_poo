package entity;


/**
 * Entidade JDBC correspondente à tabela "criadouro".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Criadouro {

    private int id;
    private String codigo;
    private String tipo;
    private String especieCriada;
    private double volumeM3;
    private double areaM2;

    public Criadouro() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Criadouro(String codigo, String tipo, String especieCriada, double volumeM3, double areaM2) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.especieCriada = especieCriada;
        this.volumeM3 = volumeM3;
        this.areaM2 = areaM2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecieCriada() {
        return especieCriada;
    }

    public void setEspecieCriada(String especieCriada) {
        this.especieCriada = especieCriada;
    }

    public double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(double volumeM3) {
        this.volumeM3 = volumeM3;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    @Override
    public String toString() {
        return "Criadouro{id=" + id + "}";
    }
}
