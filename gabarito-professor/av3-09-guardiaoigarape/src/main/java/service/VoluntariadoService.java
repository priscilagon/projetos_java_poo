package service;

import model.*;
import exception.CargaHorariaInsuficienteException;

import java.util.*;

/** Camada de serviço: voluntários, ações ambientais, participações e certificados. */
public class VoluntariadoService {

    private static final double HORAS_MINIMAS_CERTIFICADO = 10.0;

    private List<Voluntario> voluntarios = new ArrayList<>();
    private List<AcaoAmbiental> acoes = new ArrayList<>();
    private List<Participacao> participacoes = new ArrayList<>();
    private GeradorCertificado geradorCertificado = new CertificadoPadrao();

    public void cadastrarVoluntario(Voluntario v) { voluntarios.add(v); }
    public void cadastrarAcao(AcaoAmbiental a) { acoes.add(a); }
    public void registrarParticipacao(Voluntario v, AcaoAmbiental a) { participacoes.add(new Participacao(v, a)); }

    public List<Voluntario> getVoluntarios() { return voluntarios; }
    public List<AcaoAmbiental> getAcoes() { return acoes; }
    public List<Participacao> getParticipacoes() { return participacoes; }

    public Voluntario buscarVoluntarioPorNome(String nome) {
        for (Voluntario v : voluntarios) if (v.getNome().equalsIgnoreCase(nome)) return v;
        return null;
    }

    /** Soma o total de horas de voluntariado de uma pessoa em todas as ações que participou. */
    public double calcularTotalHoras(Voluntario voluntario) {
        double total = 0;
        for (Participacao p : participacoes) {
            if (p.getVoluntario() == voluntario) total += p.getAcao().getDuracaoHoras();
        }
        return total;
    }

    /** Emite o certificado, validando se a carga horária mínima foi cumprida. */
    public String emitirCertificado(Voluntario voluntario) throws CargaHorariaInsuficienteException {
        double totalHoras = calcularTotalHoras(voluntario);
        if (totalHoras < HORAS_MINIMAS_CERTIFICADO) {
            throw new CargaHorariaInsuficienteException(
                    voluntario.getNome() + " possui apenas " + totalHoras + "h. Mínimo exigido: "
                            + HORAS_MINIMAS_CERTIFICADO + "h.");
        }
        return geradorCertificado.gerar(voluntario, totalHoras);
    }

    public void imprimirRelatorioPorTipo() {
        Map<String, Integer> contagem = new HashMap<>();
        for (AcaoAmbiental a : acoes) {
            contagem.merge(a.getClass().getSimpleName(), 1, Integer::sum);
        }
        System.out.println("\n===== Ações por tipo =====");
        contagem.forEach((tipo, qtd) -> System.out.println("- " + tipo + ": " + qtd));
    }
}
