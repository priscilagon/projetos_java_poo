package entity;


/**
 * Entidade JDBC correspondente à tabela "hospede".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Hospede {

    private int id;
    private String nome;
    private String documento;
    private String tipo;

    public Hospede() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Hospede(String nome, String documento, String tipo) {
        this.nome = nome;
        this.documento = documento;
        this.tipo = tipo;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Hospede{id=" + id + "}";
    }
}
