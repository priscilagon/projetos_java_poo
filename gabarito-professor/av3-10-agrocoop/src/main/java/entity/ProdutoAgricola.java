package entity;


/**
 * Entidade JDBC correspondente à tabela "produto_agricola".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class ProdutoAgricola {

    private int id;
    private String nome;
    private String unidadeMedida;
    private double precoReferencia;

    public ProdutoAgricola() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public ProdutoAgricola(String nome, String unidadeMedida, double precoReferencia) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.precoReferencia = precoReferencia;
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

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public double getPrecoReferencia() {
        return precoReferencia;
    }

    public void setPrecoReferencia(double precoReferencia) {
        this.precoReferencia = precoReferencia;
    }

    @Override
    public String toString() {
        return "ProdutoAgricola{id=" + id + "}";
    }
}
