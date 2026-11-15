package dao;

import entity.ReservaPacote;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "reserva_pacote".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class ReservaPacoteDAO {

    private final Connection conexao;

    public ReservaPacoteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public ReservaPacote inserir(ReservaPacote obj) throws SQLException {
        String sql = "INSERT INTO reserva_pacote (reserva_id, pacote_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getReservaId());
        stmt.setInt(2, obj.getPacoteId());
            stmt.executeUpdate();
            try (ResultSet gerado = stmt.getGeneratedKeys()) {
                if (gerado.next()) {
                    obj.setId(gerado.getInt(1));
                }
            }
        }
        return obj;
    }

    /** READ - busca um registro pelo id. Retorna null se não encontrar. */
    public ReservaPacote buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reserva_pacote WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    /** READ - lista todos os registros da tabela. */
    public List<ReservaPacote> listarTodos() throws SQLException {
        List<ReservaPacote> lista = new ArrayList<>();
        String sql = "SELECT * FROM reserva_pacote ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(ReservaPacote obj) throws SQLException {
        String sql = "UPDATE reserva_pacote SET reserva_id = ?, pacote_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getReservaId());
        stmt.setInt(2, obj.getPacoteId());
            stmt.setInt(3, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM reserva_pacote WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private ReservaPacote mapear(ResultSet rs) throws SQLException {
        ReservaPacote obj = new ReservaPacote();
        obj.setId(rs.getInt("id"));
        obj.setReservaId(rs.getInt("reserva_id"));
        obj.setPacoteId(rs.getInt("pacote_id"));
        return obj;
    }
}
