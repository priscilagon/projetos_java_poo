package entity;


/**
 * Entidade JDBC correspondente à tabela "modulo".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Modulo {

    private int id;
    private int cursoId;
    private String nome;

    public Modulo() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Modulo(int cursoId, String nome) {
        this.cursoId = cursoId;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Modulo{id=" + id + "}";
    }
}
