package entity;


/**
 * Entidade JDBC correspondente à tabela "voluntario".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Voluntario {

    private int id;
    private String nome;
    private String contato;

    public Voluntario() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Voluntario(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Voluntario{id=" + id + "}";
    }
}
