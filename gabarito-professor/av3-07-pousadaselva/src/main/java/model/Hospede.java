package model;

/**
 * Classe abstrata do hóspede. HERANÇA/POLIMORFISMO: o hóspede estrangeiro
 * paga uma taxa de serviço adicional sobre o valor da estadia.
 */
public abstract class Hospede {
    private String nome;
    private String documento;

    public Hospede(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() { return nome; }
    public String getDocumento() { return documento; }

    /** Percentual de taxa de serviço adicional sobre o valor da estadia. */
    public abstract double calcularTaxaServico(double valorEstadia);

    @Override
    public String toString() {
        return nome + " (" + getClass().getSimpleName() + ")";
    }
}
