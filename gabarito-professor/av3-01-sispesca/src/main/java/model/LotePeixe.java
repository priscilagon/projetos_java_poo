package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um lote de peixes povoado em um criadouro, pertencente a um cooperado.
 * Mantém o histórico de biometrias (coleção) e o total de ração fornecida,
 * usados para calcular a taxa de conversão alimentar (TCA).
 */
public class LotePeixe {

    private String identificador;
    private Cooperado cooperado;
    private Criadouro criadouro;
    private LocalDate dataPovoamento;
    private int quantidadeAlevinos;
    private double pesoInicialMedioGramas;
    private double racaoTotalConsumidaKg;

    // Coleção: histórico de biometrias do lote ao longo do tempo
    private List<Biometria> biometrias = new ArrayList<>();

    public LotePeixe(String identificador, Cooperado cooperado, Criadouro criadouro,
                      LocalDate dataPovoamento, int quantidadeAlevinos, double pesoInicialMedioGramas) {
        this.identificador = identificador;
        this.cooperado = cooperado;
        this.criadouro = criadouro;
        this.dataPovoamento = dataPovoamento;
        this.quantidadeAlevinos = quantidadeAlevinos;
        this.pesoInicialMedioGramas = pesoInicialMedioGramas;
        this.racaoTotalConsumidaKg = 0.0;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Cooperado getCooperado() {
        return cooperado;
    }

    public Criadouro getCriadouro() {
        return criadouro;
    }

    public int getQuantidadeAlevinos() {
        return quantidadeAlevinos;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public void adicionarBiometria(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public void registrarConsumoRacao(double quilos) {
        this.racaoTotalConsumidaKg += quilos;
    }

    public double getRacaoTotalConsumidaKg() {
        return racaoTotalConsumidaKg;
    }

    /**
     * Ganho de peso médio (em gramas) desde o povoamento até a última biometria.
     * Lança LoteSemBiometriaException caso não exista nenhuma biometria registrada.
     */
    public double calcularGanhoPesoMedioGramas() throws exception.LoteSemBiometriaException {
        if (biometrias.isEmpty()) {
            throw new exception.LoteSemBiometriaException(
                    "O lote " + identificador + " ainda não possui biometrias registradas.");
        }
        Biometria ultima = biometrias.get(biometrias.size() - 1);
        return ultima.getPesoMedioGramas() - pesoInicialMedioGramas;
    }

    /**
     * Taxa de Conversão Alimentar (TCA) = ração consumida (kg) / ganho de peso total (kg).
     * Quanto menor a TCA, mais eficiente é a engorda.
     */
    public double calcularTaxaConversaoAlimentar() throws exception.LoteSemBiometriaException {
        double ganhoPesoGramasPorPeixe = calcularGanhoPesoMedioGramas();
        double ganhoPesoTotalKg = (ganhoPesoGramasPorPeixe * quantidadeAlevinos) / 1000.0;
        if (ganhoPesoTotalKg <= 0) {
            throw new exception.LoteSemBiometriaException(
                    "Ganho de peso inválido para cálculo da TCA no lote " + identificador + ".");
        }
        return racaoTotalConsumidaKg / ganhoPesoTotalKg;
    }

    @Override
    public String toString() {
        return "Lote " + identificador + " - " + criadouro.getEspecieCriada()
                + " - Povoado em " + dataPovoamento + " - " + quantidadeAlevinos + " alevinos"
                + " - Criadouro: " + criadouro.getCodigo() + " - Cooperado: " + cooperado.getNome();
    }
}
