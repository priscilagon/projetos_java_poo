package model;

/**
 * Representa um tanque-rede instalado em rio/lago.
 * A capacidade é calculada a partir do volume em m³ e de uma densidade
 * padrão de povoamento (peixes por m³), típica de sistemas em tanque-rede.
 */
public class TanqueRede extends Criadouro {

    private double volumeM3;
    private static final int DENSIDADE_PEIXES_POR_M3 = 60; // parâmetro técnico de referência

    public TanqueRede(String codigo, String especieCriada, double volumeM3) {
        super(codigo, especieCriada);
        this.volumeM3 = volumeM3;
    }

    public double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(double volumeM3) {
        this.volumeM3 = volumeM3;
    }

    // Implementação polimórfica: regra específica de tanque-rede
    @Override
    public int calcularCapacidadeMaxima() {
        return (int) Math.round(volumeM3 * DENSIDADE_PEIXES_POR_M3);
    }
}
