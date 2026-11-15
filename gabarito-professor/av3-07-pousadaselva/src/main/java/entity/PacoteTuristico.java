package entity;


/**
 * Entidade JDBC correspondente à tabela "pacote_turistico".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class PacoteTuristico {

    private int id;
    private String nome;
    private String descricao;
    private double valor;

    public PacoteTuristico() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public PacoteTuristico(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "PacoteTuristico{id=" + id + "}";
    }
}
