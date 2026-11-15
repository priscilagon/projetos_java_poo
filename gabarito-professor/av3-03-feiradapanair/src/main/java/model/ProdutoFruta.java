package model;

/** Frutas regionais (açaí, cupuaçu, tucumã): desconto de 10% a partir de 20 unidades. */
public class ProdutoFruta extends Produto {
    public ProdutoFruta(String nome, double precoUnitario, int estoqueDisponivel) {
        super(nome, precoUnitario, estoqueDisponivel);
    }

    @Override
    public double calcularPercentualDesconto(int quantidade) {
        return quantidade >= 20 ? 0.10 : 0.0;
    }
}
