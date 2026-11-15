package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "venda".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Venda {

    private int id;
    private int produtoId;
    private int feiranteId;
    private int quantidade;
    private LocalDate data;
    private double valorFinal;

    public Venda() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Venda(int produtoId, int feiranteId, int quantidade, LocalDate data, double valorFinal) {
        this.produtoId = produtoId;
        this.feiranteId = feiranteId;
        this.quantidade = quantidade;
        this.data = data;
        this.valorFinal = valorFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getFeiranteId() {
        return feiranteId;
    }

    public void setFeiranteId(int feiranteId) {
        this.feiranteId = feiranteId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    @Override
    public String toString() {
        return "Venda{id=" + id + "}";
    }
}
