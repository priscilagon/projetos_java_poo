package model;

/** Movimentação de saída: dispensação de medicamentos a pacientes. */
public class Saida extends MovimentacaoEstoque {
    public Saida(Lote lote, int quantidade) {
        super(lote, quantidade);
    }

    @Override
    public void aplicar() {
        getLote().reduzirQuantidade(getQuantidade());
        getLote().getMedicamento().ajustarEstoque(-getQuantidade());
    }
}
