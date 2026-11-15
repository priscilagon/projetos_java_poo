package model;

public class HospedeNacional extends Hospede {
    public HospedeNacional(String nome, String documento) { super(nome, documento); }

    @Override
    public double calcularTaxaServico(double valorEstadia) { return 0.0; }
}
