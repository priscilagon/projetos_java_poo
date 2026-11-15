package entity;


/**
 * Entidade JDBC correspondente à tabela "banca".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Banca {

    private int id;
    private int numero;
    private String localizacao;

    public Banca() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Banca(int numero, String localizacao) {
        this.numero = numero;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Banca{id=" + id + "}";
    }
}
