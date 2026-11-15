package model;

/** Artesanato indígena/regional: sem desconto por quantidade (peças únicas). */
public class ProdutoArtesanato extends Produto {
    public ProdutoArtesanato(String nome, double precoUnitario, int estoqueDisponivel) {
        super(nome, precoUnitario, estoqueDisponivel);
    }

    @Override
    public double calcularPercentualDesconto(int quantidade) {
        return 0.0;
    }
}
