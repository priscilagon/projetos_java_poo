package model;

/** Rota fluvial entre dois municípios, com distância e preço base da passagem. */
public class Rota {
    private String origem;
    private String destino;
    private double distanciaKm;
    private double precoBasePassagem;

    public Rota(String origem, String destino, double distanciaKm, double precoBasePassagem) {
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.precoBasePassagem = precoBasePassagem;
    }

    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getPrecoBasePassagem() { return precoBasePassagem; }

    @Override
    public String toString() {
        return origem + " -> " + destino + " (" + distanciaKm + " km)";
    }
}
