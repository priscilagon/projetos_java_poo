package model;

/** Medicamento cadastrado no estoque da farmácia comunitária. */
public class Medicamento {
    private String nome;
    private String principioAtivo;
    private int estoqueMinimo;
    private int quantidadeEmEstoque;

    public Medicamento(String nome, String principioAtivo, int estoqueMinimo) {
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.estoqueMinimo = estoqueMinimo;
        this.quantidadeEmEstoque = 0;
    }

    public String getNome() { return nome; }
    public String getPrincipioAtivo() { return principioAtivo; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public int getQuantidadeEmEstoque() { return quantidadeEmEstoque; }

    public void ajustarEstoque(int delta) { this.quantidadeEmEstoque += delta; }
    public boolean estoqueAbaixoDoMinimo() { return quantidadeEmEstoque < estoqueMinimo; }

    @Override
    public String toString() {
        return nome + " (" + principioAtivo + ") - Estoque: " + quantidadeEmEstoque
                + (estoqueAbaixoDoMinimo() ? " [ABAIXO DO MÍNIMO]" : "");
    }
}
