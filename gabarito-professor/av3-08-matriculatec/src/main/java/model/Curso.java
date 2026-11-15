package model;

import java.util.ArrayList;
import java.util.List;

/** Composição: um Curso agrega uma lista de Módulos, que agregam Disciplinas. */
public class Curso {
    private String nome;
    private int cargaHoraria;
    private List<Modulo> modulos = new ArrayList<>();

    public Curso(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() { return nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public List<Modulo> getModulos() { return modulos; }
    public void adicionarModulo(Modulo m) { modulos.add(m); }

    @Override
    public String toString() {
        return nome + " (" + cargaHoraria + "h) - " + modulos.size() + " módulo(s)";
    }
}
