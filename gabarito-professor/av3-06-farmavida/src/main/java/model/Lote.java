package model;

import java.time.LocalDate;

/** Lote de um medicamento, com validade — item central do controle de vencimento. */
public class Lote {
    private String codigo;
    private Medicamento medicamento;
    private int quantidade;
    private LocalDate validade;

    public Lote(String codigo, Medicamento medicamento, int quantidade, LocalDate validade) {
        this.codigo = codigo;
        this.medicamento = medicamento;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public String getCodigo() { return codigo; }
    public Medicamento getMedicamento() { return medicamento; }
    public int getQuantidade() { return quantidade; }
    public LocalDate getValidade() { return validade; }

    public void reduzirQuantidade(int qtd) { this.quantidade -= qtd; }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(validade);
    }

    public long diasParaVencer() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), validade);
    }

    @Override
    public String toString() {
        return "Lote " + codigo + " - " + medicamento.getNome() + " - Qtd: " + quantidade
                + " - Validade: " + validade + (estaVencido() ? " [VENCIDO]" : "");
    }
}
