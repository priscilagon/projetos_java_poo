package model;

public class HospedeEstrangeiro extends Hospede {
    private static final double PERCENTUAL_TAXA = 0.15;

    public HospedeEstrangeiro(String nome, String documento) { super(nome, documento); }

    @Override
    public double calcularTaxaServico(double valorEstadia) { return valorEstadia * PERCENTUAL_TAXA; }
}
