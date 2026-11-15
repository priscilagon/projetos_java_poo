package model;

import java.time.LocalDate;

/**
 * Classe abstrata que representa uma movimentação de estoque (entrada ou saída).
 * HERANÇA/POLIMORFISMO: cada subclasse implementa aplicar() de forma diferente
 * (entrada soma, saída subtrai) sobre o lote associado.
 */
public abstract class MovimentacaoEstoque {
    private Lote lote;
    private int quantidade;
    private LocalDate data;

    public MovimentacaoEstoque(Lote lote, int quantidade) {
        this.lote = lote;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
    }

    public Lote getLote() { return lote; }
    public int getQuantidade() { return quantidade; }
    public LocalDate getData() { return data; }

    /** Aplica o efeito da movimentação no estoque (soma ou subtrai). */
    public abstract void aplicar();

    @Override
    public String toString() {
        return String.format("%s - %s - %s - Qtd: %d", data, getClass().getSimpleName(), lote.getMedicamento().getNome(), quantidade);
    }
}
