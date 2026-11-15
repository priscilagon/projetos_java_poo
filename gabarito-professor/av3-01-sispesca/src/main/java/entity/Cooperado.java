package entity;


/**
 * Entidade JDBC correspondente à tabela "cooperado".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Cooperado {

    private int id;
    private String nome;
    private String cpf;
    private String propriedade;

    public Cooperado() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Cooperado(String nome, String cpf, String propriedade) {
        this.nome = nome;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    @Override
    public String toString() {
        return "Cooperado{id=" + id + "}";
    }
}
