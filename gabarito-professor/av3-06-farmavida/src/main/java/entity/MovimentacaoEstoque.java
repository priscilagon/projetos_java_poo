package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "movimentacao_estoque".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class MovimentacaoEstoque {

    private int id;
    private int loteId;
    private String tipo;
    private int quantidade;
    private LocalDate data;

    public MovimentacaoEstoque() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public MovimentacaoEstoque(int loteId, String tipo, int quantidade, LocalDate data) {
        this.loteId = loteId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoteId() {
        return loteId;
    }

    public void setLoteId(int loteId) {
        this.loteId = loteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return "MovimentacaoEstoque{id=" + id + "}";
    }
}
