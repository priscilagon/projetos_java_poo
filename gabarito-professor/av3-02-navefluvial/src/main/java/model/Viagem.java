package model;

import exception.CapacidadeExcedidaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma viagem: liga uma Embarcacao a uma Rota em uma data/hora
 * e mantém a lista de passagens vendidas (composição).
 */
public class Viagem {

    private Embarcacao embarcacao;
    private Rota rota;
    private LocalDateTime dataPartida;
    private List<Passagem> passagens = new ArrayList<>();

    public Viagem(Embarcacao embarcacao, Rota rota, LocalDateTime dataPartida) {
        this.embarcacao = embarcacao;
        this.rota = rota;
        this.dataPartida = dataPartida;
    }

    public Embarcacao getEmbarcacao() { return embarcacao; }
    public Rota getRota() { return rota; }
    public LocalDateTime getDataPartida() { return dataPartida; }
    public List<Passagem> getPassagens() { return passagens; }

    private long contarPorTipo(Class<? extends Passagem> tipo) {
        return passagens.stream().filter(tipo::isInstance).count();
    }

    /**
     * Vende uma passagem, validando a capacidade da embarcação conforme o tipo.
     * Lança CapacidadeExcedidaException (regra de negócio crítica do projeto).
     */
    public void venderPassagem(Passagem passagem) throws CapacidadeExcedidaException {
        if (passagem instanceof PassagemRede && contarPorTipo(PassagemRede.class) >= embarcacao.getCapacidadeRede()) {
            throw new CapacidadeExcedidaException("Capacidade de redes esgotada na embarcação " + embarcacao.getNome() + ".");
        }
        if (passagem instanceof PassagemCamarote && contarPorTipo(PassagemCamarote.class) >= embarcacao.getCapacidadeCamarote()) {
            throw new CapacidadeExcedidaException("Capacidade de camarotes esgotada na embarcação " + embarcacao.getNome() + ".");
        }
        passagens.add(passagem);
    }

    /** Taxa de ocupação geral da viagem (redes + camarotes vendidos / capacidade total). */
    public double calcularTaxaOcupacao() {
        int capacidadeTotal = embarcacao.getCapacidadeRede() + embarcacao.getCapacidadeCamarote();
        if (capacidadeTotal == 0) return 0;
        return (passagens.size() * 100.0) / capacidadeTotal;
    }

    @Override
    public String toString() {
        return rota + " - Embarcação: " + embarcacao.getNome() + " - Partida: " + dataPartida
                + " - Passagens vendidas: " + passagens.size();
    }
}
