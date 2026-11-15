package entity;

import java.time.LocalDate;

/**
 * Entidade JDBC correspondente à tabela "reserva".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Reserva {

    private int id;
    private int acomodacaoId;
    private int hospedeId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reserva() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Reserva(int acomodacaoId, int hospedeId, LocalDate checkIn, LocalDate checkOut) {
        this.acomodacaoId = acomodacaoId;
        this.hospedeId = hospedeId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcomodacaoId() {
        return acomodacaoId;
    }

    public void setAcomodacaoId(int acomodacaoId) {
        this.acomodacaoId = acomodacaoId;
    }

    public int getHospedeId() {
        return hospedeId;
    }

    public void setHospedeId(int hospedeId) {
        this.hospedeId = hospedeId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reserva{id=" + id + "}";
    }
}
