package service;

import model.*;
import exception.QuantidadeInvalidaException;

import java.util.*;

/** Camada de serviço: produtores, produtos, entregas e consolidação de receita. */
public class CooperativaAgricolaService {

    private List<Produtor> produtores = new ArrayList<>();
    private List<ProdutoAgricola> produtos = new ArrayList<>();
    private List<Entrega> entregas = new ArrayList<>();

    public void cadastrarProdutor(Produtor p) { produtores.add(p); }
    public void cadastrarProduto(ProdutoAgricola p) { produtos.add(p); }

    public List<Produtor> getProdutores() { return produtores; }
    public List<ProdutoAgricola> getProdutos() { return produtos; }
    public List<Entrega> getEntregas() { return entregas; }

    public Produtor buscarProdutorPorNome(String nome) {
        for (Produtor p : produtores) if (p.getNome().equalsIgnoreCase(nome)) return p;
        return null;
    }

    public ProdutoAgricola buscarProdutoPorNome(String nome) {
        for (ProdutoAgricola p : produtos) if (p.getNome().equalsIgnoreCase(nome)) return p;
        return null;
    }

    public void registrarEntrega(Produtor produtor, ProdutoAgricola produto, double quantidade, boolean institucional)
            throws QuantidadeInvalidaException {
        Calculavel precificacao = institucional ? new PrecificacaoInstitucional() : new PrecificacaoPadrao();
        entregas.add(new Entrega(produtor, produto, quantidade, precificacao));
    }

    public double calcularReceitaPorProdutor(Produtor produtor) {
        double total = 0;
        for (Entrega e : entregas) if (e.getProdutor() == produtor) total += e.calcularValor();
        return total;
    }

    /** Ranking de produtores por volume total entregue (em qualquer unidade, somado). */
    public List<Map.Entry<Produtor, Double>> rankingPorVolume() {
        Map<Produtor, Double> totais = new HashMap<>();
        for (Entrega e : entregas) {
            totais.merge(e.getProdutor(), e.getQuantidade(), Double::sum);
        }
        List<Map.Entry<Produtor, Double>> lista = new ArrayList<>(totais.entrySet());
        lista.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        return lista;
    }

    public Map<String, Double> consolidarEntregasPorProduto() {
        Map<String, Double> consolidado = new HashMap<>();
        for (Entrega e : entregas) {
            consolidado.merge(e.getProduto().getNome(), e.getQuantidade(), Double::sum);
        }
        return consolidado;
    }
}
