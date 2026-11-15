package service;

import model.*;
import exception.PeriodoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Camada de serviço: acomodações, hóspedes, pacotes e reservas. */
public class PousadaService {

    private List<Acomodacao> acomodacoes = new ArrayList<>();
    private List<Hospede> hospedes = new ArrayList<>();
    private List<PacoteTuristico> pacotesDisponiveis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    public void cadastrarAcomodacao(Acomodacao a) { acomodacoes.add(a); }
    public void cadastrarHospede(Hospede h) { hospedes.add(h); }
    public void cadastrarPacote(PacoteTuristico p) { pacotesDisponiveis.add(p); }

    public List<Acomodacao> getAcomodacoes() { return acomodacoes; }
    public List<Hospede> getHospedes() { return hospedes; }
    public List<PacoteTuristico> getPacotesDisponiveis() { return pacotesDisponiveis; }
    public List<Reserva> getReservas() { return reservas; }

    public Acomodacao buscarAcomodacao(String id) {
        for (Acomodacao a : acomodacoes) if (a.getIdentificacao().equalsIgnoreCase(id)) return a;
        return null;
    }

    public Hospede buscarHospede(String documento) {
        for (Hospede h : hospedes) if (h.getDocumento().equalsIgnoreCase(documento)) return h;
        return null;
    }

    public PacoteTuristico buscarPacote(String nome) {
        for (PacoteTuristico p : pacotesDisponiveis) if (p.getNome().equalsIgnoreCase(nome)) return p;
        return null;
    }

    public Reserva criarReserva(Acomodacao a, Hospede h, LocalDate checkIn, LocalDate checkOut) throws PeriodoInvalidoException {
        Reserva reserva = new Reserva(a, h, checkIn, checkOut);
        reservas.add(reserva);
        return reserva;
    }
}
