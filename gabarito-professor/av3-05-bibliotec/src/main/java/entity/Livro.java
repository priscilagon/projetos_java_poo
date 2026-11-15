package entity;


/**
 * Entidade JDBC correspondente à tabela "livro".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int exemplaresTotal;
    private int exemplaresDisponiveis;
    private int prazoEmprestimoDias;

    public Livro() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Livro(String titulo, String autor, String categoria, int exemplaresTotal, int exemplaresDisponiveis, int prazoEmprestimoDias) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.exemplaresTotal = exemplaresTotal;
        this.exemplaresDisponiveis = exemplaresDisponiveis;
        this.prazoEmprestimoDias = prazoEmprestimoDias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getExemplaresTotal() {
        return exemplaresTotal;
    }

    public void setExemplaresTotal(int exemplaresTotal) {
        this.exemplaresTotal = exemplaresTotal;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public void setExemplaresDisponiveis(int exemplaresDisponiveis) {
        this.exemplaresDisponiveis = exemplaresDisponiveis;
    }

    public int getPrazoEmprestimoDias() {
        return prazoEmprestimoDias;
    }

    public void setPrazoEmprestimoDias(int prazoEmprestimoDias) {
        this.prazoEmprestimoDias = prazoEmprestimoDias;
    }

    @Override
    public String toString() {
        return "Livro{id=" + id + "}";
    }
}
