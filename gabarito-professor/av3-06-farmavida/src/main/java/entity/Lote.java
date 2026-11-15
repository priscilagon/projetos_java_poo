package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "lote".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Lote {

    private int id;
    private String codigo;
    private int medicamentoId;
    private int quantidade;
    private LocalDate validade;

    public Lote() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Lote(String codigo, int medicamentoId, int quantidade, LocalDate validade) {
        this.codigo = codigo;
        this.medicamentoId = medicamentoId;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Lote{id=" + id + "}";
    }
}
