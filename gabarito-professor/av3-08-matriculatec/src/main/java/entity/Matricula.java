package entity;


/**
 * Entidade JDBC correspondente à tabela "matricula".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Matricula {

    private int id;
    private int alunoId;
    private int turmaId;
    private String situacao;

    public Matricula() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Matricula(int alunoId, int turmaId, String situacao) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Matricula{id=" + id + "}";
    }
}
