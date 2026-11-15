package service;

import model.*;
import exception.MedicamentoVencidoException;

import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: cadastro de medicamentos, lotes e movimentações. */
public class FarmaciaService {

    private List<Medicamento> medicamentos = new ArrayList<>();
    private List<Lote> lotes = new ArrayList<>();
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    public void cadastrarMedicamento(Medicamento m) { medicamentos.add(m); }
    public void cadastrarLote(Lote l) { lotes.add(l); }

    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Lote> getLotes() { return lotes; }
    public List<MovimentacaoEstoque> getMovimentacoes() { return movimentacoes; }

    public Medicamento buscarMedicamentoPorNome(String nome) {
        for (Medicamento m : medicamentos) if (m.getNome().equalsIgnoreCase(nome)) return m;
        return null;
    }

    public Lote buscarLotePorCodigo(String codigo) {
        for (Lote l : lotes) if (l.getCodigo().equalsIgnoreCase(codigo)) return l;
        return null;
    }

    public void registrarEntrada(Lote lote, int quantidade) {
        Entrada entrada = new Entrada(lote, quantidade);
        entrada.aplicar();
        movimentacoes.add(entrada);
    }

    /** Dispensa (saída) de medicamento, bloqueando lotes vencidos ou sem quantidade suficiente. */
    public void registrarSaida(Lote lote, int quantidade) throws MedicamentoVencidoException {
        if (lote.estaVencido()) {
            throw new MedicamentoVencidoException("Lote " + lote.getCodigo() + " está VENCIDO. Dispensação bloqueada.");
        }
        if (quantidade > lote.getQuantidade()) {
            throw new MedicamentoVencidoException("Quantidade solicitada excede o saldo do lote " + lote.getCodigo() + ".");
        }
        Saida saida = new Saida(lote, quantidade);
        saida.aplicar();
        movimentacoes.add(saida);
    }

    public void imprimirAlertasValidade() {
        System.out.println("\n===== Alertas de validade (até 30 dias) =====");
        boolean algum = false;
        for (Lote l : lotes) {
            if (!l.estaVencido() && l.diasParaVencer() <= 30) {
                System.out.println("- " + l + " (vence em " + l.diasParaVencer() + " dia(s))");
                algum = true;
            }
        }
        if (!algum) System.out.println("Nenhum lote próximo do vencimento.");
    }

    public void imprimirAlertasEstoqueBaixo() {
        System.out.println("\n===== Alertas de estoque abaixo do mínimo =====");
        boolean algum = false;
        for (Medicamento m : medicamentos) {
            if (m.estoqueAbaixoDoMinimo()) {
                System.out.println("- " + m);
                algum = true;
            }
        }
        if (!algum) System.out.println("Nenhum medicamento abaixo do estoque mínimo.");
    }
}
