package model;

import exception.EstoqueInsuficienteException;
import java.time.LocalDate;

/** Registro de uma venda: um produto, quantidade, feirante e data. */
public class Venda {
    private Produto produto;
    private int quantidade;
    private LocalDate data;
    private Feirante feirante;
    private double valorFinal;

    public Venda(Produto produto, int quantidade, Feirante feirante) throws EstoqueInsuficienteException {
        if (quantidade > produto.getEstoqueDisponivel()) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente de " + produto.getNome() + ". Disponível: " + produto.getEstoqueDisponivel());
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.feirante = feirante;
        this.data = LocalDate.now();
        this.valorFinal = produto.calcularValorVenda(quantidade);
        produto.baixarEstoque(quantidade);
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public LocalDate getData() { return data; }
    public Feirante getFeirante() { return feirante; }
    public double getValorFinal() { return valorFinal; }

    @Override
    public String toString() {
        return String.format("%s - %dx %s - R$ %.2f - %s", data, quantidade, produto.getNome(), valorFinal, feirante.getNome());
    }
}
