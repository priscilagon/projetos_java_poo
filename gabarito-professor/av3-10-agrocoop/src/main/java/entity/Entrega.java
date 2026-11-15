package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "entrega".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Entrega {

    private int id;
    private int produtorId;
    private int produtoId;
    private double quantidade;
    private LocalDate data;
    private String destino;

    public Entrega() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Entrega(int produtorId, int produtoId, double quantidade, LocalDate data, String destino) {
        this.produtorId = produtorId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.data = data;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutorId() {
        return produtorId;
    }

    public void setProdutorId(int produtorId) {
        this.produtorId = produtorId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Entrega{id=" + id + "}";
    }
}
