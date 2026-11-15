package entity;


/**
 * Entidade JDBC correspondente à tabela "rota".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Rota {

    private int id;
    private String origem;
    private String destino;
    private double distanciaKm;
    private double precoBasePassagem;

    public Rota() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Rota(String origem, String destino, double distanciaKm, double precoBasePassagem) {
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.precoBasePassagem = precoBasePassagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getPrecoBasePassagem() {
        return precoBasePassagem;
    }

    public void setPrecoBasePassagem(double precoBasePassagem) {
        this.precoBasePassagem = precoBasePassagem;
    }

    @Override
    public String toString() {
        return "Rota{id=" + id + "}";
    }
}
