package model;

import exception.PeriodoInvalidoException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/** Reserva de uma acomodação por um hóspede, com pacotes turísticos contratados (composição). */
public class Reserva {
    private Acomodacao acomodacao;
    private Hospede hospede;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private List<PacoteTuristico> pacotes = new ArrayList<>();

    public Reserva(Acomodacao acomodacao, Hospede hospede, LocalDate checkIn, LocalDate checkOut) throws PeriodoInvalidoException {
        if (!checkOut.isAfter(checkIn)) {
            throw new PeriodoInvalidoException("A data de check-out deve ser posterior à data de check-in.");
        }
        this.acomodacao = acomodacao;
        this.hospede = hospede;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Acomodacao getAcomodacao() { return acomodacao; }
    public Hospede getHospede() { return hospede; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public List<PacoteTuristico> getPacotes() { return pacotes; }

    public void adicionarPacote(PacoteTuristico pacote) { pacotes.add(pacote); }

    public long calcularNumeroDiarias() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    /** Valor total = diárias × valor da diária + pacotes + taxa de serviço (se estrangeiro). */
    public double calcularValorTotal() {
        double valorDiarias = calcularNumeroDiarias() * acomodacao.valorDiaria();
        double valorPacotes = pacotes.stream().mapToDouble(PacoteTuristico::getValor).sum();
        double subtotal = valorDiarias + valorPacotes;
        double taxaServico = hospede.calcularTaxaServico(subtotal);
        return subtotal + taxaServico;
    }

    @Override
    public String toString() {
        return acomodacao.getIdentificacao() + " - " + hospede.getNome() + " - " + checkIn + " a " + checkOut
                + " - Total: R$ " + String.format("%.2f", calcularValorTotal());
    }
}
