package entity;


/**
 * Entidade JDBC correspondente à tabela "produtor".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Produtor {

    private int id;
    private String nome;
    private String comunidade;
    private String propriedade;

    public Produtor() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Produtor(String nome, String comunidade, String propriedade) {
        this.nome = nome;
        this.comunidade = comunidade;
        this.propriedade = propriedade;
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

    public String getComunidade() {
        return comunidade;
    }

    public void setComunidade(String comunidade) {
        this.comunidade = comunidade;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    @Override
    public String toString() {
        return "Produtor{id=" + id + "}";
    }
}
