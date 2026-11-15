package service;

import model.*;
import exception.LoteSemBiometriaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Camada de serviço: concentra as regras de negócio da cooperativa
 * (cadastro de cooperados, criadouros e lotes; cálculo de relatórios).
 * Separa a lógica de negócio da interface de console (Main), seguindo
 * a organização em pacotes exigida (model / service / exception / main).
 */
public class CooperativaService {

    private List<Cooperado> cooperados = new ArrayList<>();
    private List<Criadouro> criadouros = new ArrayList<>();
    private List<LotePeixe> lotes = new ArrayList<>();

    public void cadastrarCooperado(Cooperado c) {
        cooperados.add(c);
    }

    public void cadastrarCriadouro(Criadouro c) {
        criadouros.add(c);
    }

    public void cadastrarLote(LotePeixe lote) {
        lotes.add(lote);
    }

    public List<Cooperado> getCooperados() {
        return cooperados;
    }

    public List<Criadouro> getCriadouros() {
        return criadouros;
    }

    public List<LotePeixe> getLotes() {
        return lotes;
    }

    public Cooperado buscarCooperadoPorCpf(String cpf) {
        for (Cooperado c : cooperados) {
            if (c.getCpf().equalsIgnoreCase(cpf)) return c;
        }
        return null;
    }

    public Criadouro buscarCriadouroPorCodigo(String codigo) {
        for (Criadouro c : criadouros) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) return c;
        }
        return null;
    }

    public LotePeixe buscarLotePorIdentificador(String id) {
        for (LotePeixe l : lotes) {
            if (l.getIdentificador().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    public boolean removerLote(String id) {
        LotePeixe lote = buscarLotePorIdentificador(id);
        if (lote != null) {
            lotes.remove(lote);
            return true;
        }
        return false;
    }

    /**
     * Gera relatório de produtividade (TCA) de todos os lotes.
     * Trata individualmente os lotes sem biometria, sem interromper o relatório geral.
     */
    public void imprimirRelatorioProdutividade() {
        System.out.println("\n===== RELATÓRIO DE PRODUTIVIDADE (TCA por Lote) =====");
        if (lotes.isEmpty()) {
            System.out.println("Nenhum lote cadastrado.");
            return;
        }
        for (LotePeixe lote : lotes) {
            try {
                double tca = lote.calcularTaxaConversaoAlimentar();
                System.out.printf("%s -> TCA: %.2f (quanto menor, mais eficiente)%n",
                        lote.getIdentificador(), tca);
            } catch (LoteSemBiometriaException e) {
                System.out.println(lote.getIdentificador() + " -> " + e.getMessage());
            }
        }
    }
}
