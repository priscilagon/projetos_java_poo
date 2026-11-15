package model;

/**
 * Classe abstrata que representa um produto regional vendido na feira.
 * HERANÇA/POLIMORFISMO: cada categoria de produto define seu próprio
 * percentual de desconto para compras em grande quantidade.
 */
public abstract class Produto {

    private String nome;
    private double precoUnitario;
    private int estoqueDisponivel;

    public Produto(String nome, double precoUnitario, int estoqueDisponivel) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public String getNome() { return nome; }
    public double getPrecoUnitario() { return precoUnitario; }
    public int getEstoqueDisponivel() { return estoqueDisponivel; }

    public void baixarEstoque(int quantidade) {
        this.estoqueDisponivel -= quantidade;
    }

    /** Percentual de desconto aplicado por categoria quando a venda é grande. */
    public abstract double calcularPercentualDesconto(int quantidade);

    public double calcularValorVenda(int quantidade) {
        double bruto = precoUnitario * quantidade;
        double desconto = bruto * calcularPercentualDesconto(quantidade);
        return bruto - desconto;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - R$ %.2f/un - Estoque: %d",
                nome, getClass().getSimpleName(), precoUnitario, estoqueDisponivel);
    }
}
