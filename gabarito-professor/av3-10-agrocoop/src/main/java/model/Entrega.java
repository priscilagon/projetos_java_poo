package model;

import exception.QuantidadeInvalidaException;
import java.time.LocalDate;

/** Registro de entrega de produção de um produtor, com cálculo de valor via estratégia Calculavel. */
public class Entrega {
    private Produtor produtor;
    private ProdutoAgricola produto;
    private double quantidade;
    private LocalDate data;
    private Calculavel precificacao;

    public Entrega(Produtor produtor, ProdutoAgricola produto, double quantidade, Calculavel precificacao)
            throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade entregue deve ser maior que zero.");
        }
        this.produtor = produtor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        this.precificacao = precificacao;
    }

    public Produtor getProdutor() { return produtor; }
    public ProdutoAgricola getProduto() { return produto; }
    public double getQuantidade() { return quantidade; }
    public LocalDate getData() { return data; }

    public double calcularValor() {
        return precificacao.calcularValor(quantidade, produto.getPrecoReferencia());
    }

    @Override
    public String toString() {
        return data + " - " + produtor.getNome() + " -> " + quantidade + " " + produto.getUnidadeMedida()
                + " de " + produto.getNome() + " - R$ " + String.format("%.2f", calcularValor());
    }
}
