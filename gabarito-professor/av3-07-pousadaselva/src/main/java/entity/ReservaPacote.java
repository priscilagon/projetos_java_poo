package entity;


/**
 * Entidade JDBC correspondente à tabela "reserva_pacote".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class ReservaPacote {

    private int id;
    private int reservaId;
    private int pacoteId;

    public ReservaPacote() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public ReservaPacote(int reservaId, int pacoteId) {
        this.reservaId = reservaId;
        this.pacoteId = pacoteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(int pacoteId) {
        this.pacoteId = pacoteId;
    }

    @Override
    public String toString() {
        return "ReservaPacote{id=" + id + "}";
    }
}
