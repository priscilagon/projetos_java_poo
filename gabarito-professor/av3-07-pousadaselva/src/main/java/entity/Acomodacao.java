package entity;


/**
 * Entidade JDBC correspondente à tabela "acomodacao".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Acomodacao {

    private int id;
    private String identificacao;
    private String tipo;
    private double valorDiaria;

    public Acomodacao() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Acomodacao(String identificacao, String tipo, double valorDiaria) {
        this.identificacao = identificacao;
        this.tipo = tipo;
        this.valorDiaria = valorDiaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return "Acomodacao{id=" + id + "}";
    }
}
