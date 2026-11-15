package entity;


/**
 * Entidade JDBC correspondente à tabela "passagem".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Passagem {

    private int id;
    private int viagemId;
    private String tipo;
    private String passageiroNome;
    private String passageiroCpf;
    private String passageiroDocumento;
    private double valor;

    public Passagem() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Passagem(int viagemId, String tipo, String passageiroNome, String passageiroCpf, String passageiroDocumento, double valor) {
        this.viagemId = viagemId;
        this.tipo = tipo;
        this.passageiroNome = passageiroNome;
        this.passageiroCpf = passageiroCpf;
        this.passageiroDocumento = passageiroDocumento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassageiroNome() {
        return passageiroNome;
    }

    public void setPassageiroNome(String passageiroNome) {
        this.passageiroNome = passageiroNome;
    }

    public String getPassageiroCpf() {
        return passageiroCpf;
    }

    public void setPassageiroCpf(String passageiroCpf) {
        this.passageiroCpf = passageiroCpf;
    }

    public String getPassageiroDocumento() {
        return passageiroDocumento;
    }

    public void setPassageiroDocumento(String passageiroDocumento) {
        this.passageiroDocumento = passageiroDocumento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Passagem{id=" + id + "}";
    }
}
