package entity;


/**
 * Entidade JDBC correspondente à tabela "participacao".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Participacao {

    private int id;
    private int voluntarioId;
    private int acaoId;

    public Participacao() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Participacao(int voluntarioId, int acaoId) {
        this.voluntarioId = voluntarioId;
        this.acaoId = acaoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(int voluntarioId) {
        this.voluntarioId = voluntarioId;
    }

    public int getAcaoId() {
        return acaoId;
    }

    public void setAcaoId(int acaoId) {
        this.acaoId = acaoId;
    }

    @Override
    public String toString() {
        return "Participacao{id=" + id + "}";
    }
}
