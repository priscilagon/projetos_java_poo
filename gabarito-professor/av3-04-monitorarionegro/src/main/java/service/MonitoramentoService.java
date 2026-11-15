package service;

import model.*;
import exception.MedicaoInvalidaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: cadastro de estações, registro de medições e alertas. */
public class MonitoramentoService {

    private static final double NIVEL_MINIMO_PLAUSIVEL = 0.0;
    private static final double NIVEL_MAXIMO_PLAUSIVEL = 35.0;
    private static final double LIMITE_ALERTA_CHEIA = 29.0;
    private static final double LIMITE_ALERTA_SECA = 15.0;

    private List<EstacaoMonitoramento> estacoes = new ArrayList<>();

    public void cadastrarEstacao(EstacaoMonitoramento e) { estacoes.add(e); }
    public List<EstacaoMonitoramento> getEstacoes() { return estacoes; }

    public EstacaoMonitoramento buscarPorNome(String nome) {
        for (EstacaoMonitoramento e : estacoes) if (e.getNome().equalsIgnoreCase(nome)) return e;
        return null;
    }

    /**
     * Registra uma medição validando se o valor é plausível (regra de negócio).
     * Retorna uma mensagem de alerta caso o nível seja crítico, ou null caso contrário.
     */
    public String registrarMedicao(EstacaoMonitoramento estacao, double nivel) throws MedicaoInvalidaException {
        if (nivel < NIVEL_MINIMO_PLAUSIVEL || nivel > NIVEL_MAXIMO_PLAUSIVEL) {
            throw new MedicaoInvalidaException(
                    "Nível " + nivel + "m fora da faixa plausível (0 a 35m) para a estação " + estacao.getNome() + ".");
        }
        estacao.registrarMedicao(new Medicao(LocalDate.now(), nivel));
        if (nivel >= LIMITE_ALERTA_CHEIA) {
            return "ALERTA DE CHEIA CRÍTICA em " + estacao.getNome() + "!";
        }
        if (nivel <= LIMITE_ALERTA_SECA) {
            return "ALERTA DE SECA CRÍTICA em " + estacao.getNome() + "!";
        }
        return null;
    }

    public void compararEstacoes(EstacaoMonitoramento a, EstacaoMonitoramento b) {
        Medicao ultimaA = a.getUltimaMedicao();
        Medicao ultimaB = b.getUltimaMedicao();
        System.out.println("\n===== Comparativo de Estações =====");
        System.out.println(a.getNome() + ": " + (ultimaA != null ? ultimaA.getNivelMetros() + "m (" + a.classificarUltimoNivel() + ")" : "sem dados"));
        System.out.println(b.getNome() + ": " + (ultimaB != null ? ultimaB.getNivelMetros() + "m (" + b.classificarUltimoNivel() + ")" : "sem dados"));
        if (ultimaA != null && ultimaB != null) {
            double diferenca = Math.abs(ultimaA.getNivelMetros() - ultimaB.getNivelMetros());
            System.out.printf("Diferença entre estações: %.2fm%n", diferenca);
        }
    }
}
