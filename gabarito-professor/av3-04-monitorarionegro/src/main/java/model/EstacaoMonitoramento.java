package model;

import java.util.ArrayList;
import java.util.List;

/** Estação de monitoramento hidrológico, com histórico de medições (coleção). */
public class EstacaoMonitoramento {
    private String nome;
    private String municipio;
    private ClassificadorNivel classificador;
    private List<Medicao> medicoes = new ArrayList<>();

    public EstacaoMonitoramento(String nome, String municipio, ClassificadorNivel classificador) {
        this.nome = nome;
        this.municipio = municipio;
        this.classificador = classificador;
    }

    public String getNome() { return nome; }
    public String getMunicipio() { return municipio; }
    public List<Medicao> getMedicoes() { return medicoes; }

    public void registrarMedicao(Medicao medicao) {
        medicoes.add(medicao);
    }

    public Medicao getUltimaMedicao() {
        if (medicoes.isEmpty()) return null;
        return medicoes.get(medicoes.size() - 1);
    }

    public String classificarUltimoNivel() {
        Medicao ultima = getUltimaMedicao();
        if (ultima == null) return "SEM DADOS";
        return classificador.classificar(ultima.getNivelMetros());
    }

    @Override
    public String toString() {
        return nome + " (" + municipio + ")";
    }
}
