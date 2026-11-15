package model;

/** Espaço físico de venda ocupado por um feirante. */
public class Banca {
    private int numero;
    private String localizacao;

    public Banca(int numero, String localizacao) {
        this.numero = numero;
        this.localizacao = localizacao;
    }

    public int getNumero() { return numero; }
    public String getLocalizacao() { return localizacao; }

    @Override
    public String toString() {
        return "Banca " + numero + " (" + localizacao + ")";
    }
}
