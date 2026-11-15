package model;

/** Livro/exemplar do acervo da biblioteca do CETAM. */
public class Livro {
    private String titulo;
    private String autor;
    private String categoria; // técnico, literatura, referência
    private int exemplaresTotal;
    private int exemplaresDisponiveis;
    private int prazoEmprestimoDias;
    private CalculadoraMulta calculadoraMulta;

    public Livro(String titulo, String autor, String categoria, int exemplaresTotal, int prazoEmprestimoDias) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.exemplaresTotal = exemplaresTotal;
        this.exemplaresDisponiveis = exemplaresTotal;
        this.prazoEmprestimoDias = prazoEmprestimoDias;
        this.calculadoraMulta = categoria.equalsIgnoreCase("referência") ? new MultaReferencia() : new MultaPadrao();
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public int getExemplaresDisponiveis() { return exemplaresDisponiveis; }
    public int getPrazoEmprestimoDias() { return prazoEmprestimoDias; }
    public CalculadoraMulta getCalculadoraMulta() { return calculadoraMulta; }

    public void reservarExemplar() { exemplaresDisponiveis--; }
    public void devolverExemplar() { exemplaresDisponiveis++; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " [" + categoria + "] - Disponíveis: " + exemplaresDisponiveis + "/" + exemplaresTotal;
    }
}
