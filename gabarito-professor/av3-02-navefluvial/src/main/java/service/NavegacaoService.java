package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: cadastro de embarcações, rotas, viagens e relatórios. */
public class NavegacaoService {

    private List<Embarcacao> embarcacoes = new ArrayList<>();
    private List<Rota> rotas = new ArrayList<>();
    private List<Viagem> viagens = new ArrayList<>();

    public void cadastrarEmbarcacao(Embarcacao e) { embarcacoes.add(e); }
    public void cadastrarRota(Rota r) { rotas.add(r); }
    public void cadastrarViagem(Viagem v) { viagens.add(v); }

    public List<Embarcacao> getEmbarcacoes() { return embarcacoes; }
    public List<Rota> getRotas() { return rotas; }
    public List<Viagem> getViagens() { return viagens; }

    public Embarcacao buscarEmbarcacao(String nome) {
        for (Embarcacao e : embarcacoes) if (e.getNome().equalsIgnoreCase(nome)) return e;
        return null;
    }

    public Rota buscarRota(int indice) {
        if (indice < 0 || indice >= rotas.size()) return null;
        return rotas.get(indice);
    }

    public Viagem buscarViagem(int indice) {
        if (indice < 0 || indice >= viagens.size()) return null;
        return viagens.get(indice);
    }

    public void imprimirRelatorioOcupacao() {
        System.out.println("\n===== RELATÓRIO DE OCUPAÇÃO POR VIAGEM =====");
        if (viagens.isEmpty()) {
            System.out.println("Nenhuma viagem cadastrada.");
            return;
        }
        for (int i = 0; i < viagens.size(); i++) {
            Viagem v = viagens.get(i);
            System.out.printf("%d) %s -> Ocupação: %.1f%%%n", i, v, v.calcularTaxaOcupacao());
        }
    }
}
