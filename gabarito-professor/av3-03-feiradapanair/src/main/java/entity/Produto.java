package entity;


/**
 * Entidade JDBC correspondente à tabela "produto".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Produto {

    private int id;
    private String nome;
    private String categoria;
    private double precoUnitario;
    private int estoqueDisponivel;

    public Produto() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Produto(String nome, String categoria, double precoUnitario, int estoqueDisponivel) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.estoqueDisponivel = estoqueDisponivel;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public void setEstoqueDisponivel(int estoqueDisponivel) {
        this.estoqueDisponivel = estoqueDisponivel;
    }

    @Override
    public String toString() {
        return "Produto{id=" + id + "}";
    }
}
