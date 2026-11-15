package model;

/** Movimentação de entrada: compra/recebimento de medicamentos. */
public class Entrada extends MovimentacaoEstoque {
    public Entrada(Lote lote, int quantidade) {
        super(lote, quantidade);
    }

    @Override
    public void aplicar() {
        getLote().reduzirQuantidade(-getQuantidade()); // soma (reduz negativo = aumenta)
        getLote().getMedicamento().ajustarEstoque(getQuantidade());
    }
}
