package model;

/** Pescado regional (tambaqui, tucunaré): desconto de 5% a partir de 10kg (alto valor agregado). */
public class ProdutoPescado extends Produto {
    public ProdutoPescado(String nome, double precoUnitario, int estoqueDisponivel) {
        super(nome, precoUnitario, estoqueDisponivel);
    }

    @Override
    public double calcularPercentualDesconto(int quantidade) {
        return quantidade >= 10 ? 0.05 : 0.0;
    }
}
