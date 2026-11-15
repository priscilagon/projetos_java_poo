package entity;

import java.time.LocalDateTime;

/**
 * Entidade JDBC correspondente à tabela "viagem".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Viagem {

    private int id;
    private int embarcacaoId;
    private int rotaId;
    private LocalDateTime dataPartida;

    public Viagem() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Viagem(int embarcacaoId, int rotaId, LocalDateTime dataPartida) {
        this.embarcacaoId = embarcacaoId;
        this.rotaId = rotaId;
        this.dataPartida = dataPartida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmbarcacaoId() {
        return embarcacaoId;
    }

    public void setEmbarcacaoId(int embarcacaoId) {
        this.embarcacaoId = embarcacaoId;
    }

    public int getRotaId() {
        return rotaId;
    }

    public void setRotaId(int rotaId) {
        this.rotaId = rotaId;
    }

    public LocalDateTime getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDateTime dataPartida) {
        this.dataPartida = dataPartida;
    }

    @Override
    public String toString() {
        return "Viagem{id=" + id + "}";
    }
}
