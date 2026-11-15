package dao;

import entity.Reserva;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "reserva".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class ReservaDAO {

    private final Connection conexao;

    public ReservaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Reserva inserir(Reserva obj) throws SQLException {
        String sql = "INSERT INTO reserva (acomodacao_id, hospede_id, check_in, check_out) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getAcomodacaoId());
        stmt.setInt(2, obj.getHospedeId());
        stmt.setObject(3, obj.getCheckIn());
        stmt.setObject(4, obj.getCheckOut());
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
    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE id = ?";
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
    public List<Reserva> listarTodos() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reserva ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Reserva obj) throws SQLException {
        String sql = "UPDATE reserva SET acomodacao_id = ?, hospede_id = ?, check_in = ?, check_out = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getAcomodacaoId());
        stmt.setInt(2, obj.getHospedeId());
        stmt.setObject(3, obj.getCheckIn());
        stmt.setObject(4, obj.getCheckOut());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva obj = new Reserva();
        obj.setId(rs.getInt("id"));
        obj.setAcomodacaoId(rs.getInt("acomodacao_id"));
        obj.setHospedeId(rs.getInt("hospede_id"));
        obj.setCheckIn(rs.getObject("check_in", LocalDate.class));
        obj.setCheckOut(rs.getObject("check_out", LocalDate.class));
        return obj;
    }
}
