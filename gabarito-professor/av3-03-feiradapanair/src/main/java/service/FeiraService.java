package service;

import model.*;
import exception.EstoqueInsuficienteException;

import java.util.*;

/** Camada de serviço: cadastro de feirantes, produtos e registro/relatório de vendas. */
public class FeiraService {

    private List<Feirante> feirantes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    public void cadastrarFeirante(Feirante f) { feirantes.add(f); }
    public void cadastrarProduto(Produto p) { produtos.add(p); }

    public List<Feirante> getFeirantes() { return feirantes; }
    public List<Produto> getProdutos() { return produtos; }
    public List<Venda> getVendas() { return vendas; }

    public Feirante buscarFeirantePorCpf(String cpf) {
        for (Feirante f : feirantes) if (f.getCpf().equalsIgnoreCase(cpf)) return f;
        return null;
    }

    public Produto buscarProdutoPorNome(String nome) {
        for (Produto p : produtos) if (p.getNome().equalsIgnoreCase(nome)) return p;
        return null;
    }

    public void registrarVenda(Produto produto, int quantidade, Feirante feirante) throws EstoqueInsuficienteException {
        vendas.add(new Venda(produto, quantidade, feirante));
    }

    public double calcularFaturamentoTotal() {
        double total = 0;
        for (Venda v : vendas) total += v.getValorFinal();
        return total;
    }

    public double calcularFaturamentoPorFeirante(Feirante feirante) {
        double total = 0;
        for (Venda v : vendas) if (v.getFeirante() == feirante) total += v.getValorFinal();
        return total;
    }

    /** Retorna o(s) produto(s) mais vendido(s) em quantidade total. */
    public List<String> produtosMaisVendidos() {
        Map<String, Integer> totalPorProduto = new HashMap<>();
        for (Venda v : vendas) {
            totalPorProduto.merge(v.getProduto().getNome(), v.getQuantidade(), Integer::sum);
        }
        if (totalPorProduto.isEmpty()) return List.of();
        int max = Collections.max(totalPorProduto.values());
        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : totalPorProduto.entrySet()) {
            if (entry.getValue() == max) resultado.add(entry.getKey() + " (" + entry.getValue() + " unid.)");
        }
        return resultado;
    }
}
