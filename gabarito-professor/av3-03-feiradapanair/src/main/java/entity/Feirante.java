package entity;


/**
 * Entidade JDBC correspondente à tabela "feirante".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Feirante {

    private int id;
    private String nome;
    private String cpf;
    private int bancaId;

    public Feirante() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Feirante(String nome, String cpf, int bancaId) {
        this.nome = nome;
        this.cpf = cpf;
        this.bancaId = bancaId;
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

    public int getBancaId() {
        return bancaId;
    }

    public void setBancaId(int bancaId) {
        this.bancaId = bancaId;
    }

    @Override
    public String toString() {
        return "Feirante{id=" + id + "}";
    }
}
