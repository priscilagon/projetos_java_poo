package model;

import java.util.ArrayList;
import java.util.List;

/** Composição: um Módulo agrega uma lista de Disciplinas. */
public class Modulo {
    private String nome;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Modulo(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public void adicionarDisciplina(Disciplina d) { disciplinas.add(d); }

    public int cargaHorariaTotal() {
        return disciplinas.stream().mapToInt(Disciplina::getCargaHoraria).sum();
    }

    @Override
    public String toString() {
        return nome + " - " + disciplinas.size() + " disciplina(s) - " + cargaHorariaTotal() + "h";
    }
}
