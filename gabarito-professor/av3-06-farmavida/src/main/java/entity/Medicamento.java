package entity;


/**
 * Entidade JDBC correspondente à tabela "medicamento".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Medicamento {

    private int id;
    private String nome;
    private String principioAtivo;
    private int estoqueMinimo;

    public Medicamento() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Medicamento(String nome, String principioAtivo, int estoqueMinimo) {
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.estoqueMinimo = estoqueMinimo;
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

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    @Override
    public String toString() {
        return "Medicamento{id=" + id + "}";
    }
}
