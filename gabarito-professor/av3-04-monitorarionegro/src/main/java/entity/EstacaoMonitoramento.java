package entity;


/**
 * Entidade JDBC correspondente à tabela "estacao_monitoramento".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class EstacaoMonitoramento {

    private int id;
    private String nome;
    private String municipio;

    public EstacaoMonitoramento() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public EstacaoMonitoramento(String nome, String municipio) {
        this.nome = nome;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "EstacaoMonitoramento{id=" + id + "}";
    }
}
