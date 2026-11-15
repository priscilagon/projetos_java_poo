package entity;


/**
 * Entidade JDBC correspondente à tabela "turma".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Turma {

    private int id;
    private int moduloId;
    private String codigo;
    private int limiteVagas;

    public Turma() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Turma(int moduloId, String codigo, int limiteVagas) {
        this.moduloId = moduloId;
        this.codigo = codigo;
        this.limiteVagas = limiteVagas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuloId() {
        return moduloId;
    }

    public void setModuloId(int moduloId) {
        this.moduloId = moduloId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getLimiteVagas() {
        return limiteVagas;
    }

    public void setLimiteVagas(int limiteVagas) {
        this.limiteVagas = limiteVagas;
    }

    @Override
    public String toString() {
        return "Turma{id=" + id + "}";
    }
}
