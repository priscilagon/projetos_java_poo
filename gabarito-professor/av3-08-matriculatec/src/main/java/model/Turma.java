package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private Modulo modulo;
    private int limiteVagas;
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    public Turma(String codigo, Modulo modulo, int limiteVagas) {
        this.codigo = codigo;
        this.modulo = modulo;
        this.limiteVagas = limiteVagas;
    }

    public String getCodigo() { return codigo; }
    public Modulo getModulo() { return modulo; }
    public int getLimiteVagas() { return limiteVagas; }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }

    public boolean temVagaDisponivel() {
        return alunosMatriculados.size() < limiteVagas;
    }

    public double calcularTaxaOcupacao() {
        return (alunosMatriculados.size() * 100.0) / limiteVagas;
    }

    @Override
    public String toString() {
        return codigo + " - " + modulo.getNome() + " - Vagas: " + alunosMatriculados.size() + "/" + limiteVagas;
    }
}
