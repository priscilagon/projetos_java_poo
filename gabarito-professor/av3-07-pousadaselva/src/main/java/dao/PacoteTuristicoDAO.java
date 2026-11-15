package dao;

import entity.PacoteTuristico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "pacote_turistico".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class PacoteTuristicoDAO {

    private final Connection conexao;

    public PacoteTuristicoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public PacoteTuristico inserir(PacoteTuristico obj) throws SQLException {
        String sql = "INSERT INTO pacote_turistico (nome, descricao, valor) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getDescricao());
        stmt.setDouble(3, obj.getValor());
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
    public PacoteTuristico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pacote_turistico WHERE id = ?";
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
    public List<PacoteTuristico> listarTodos() throws SQLException {
        List<PacoteTuristico> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacote_turistico ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(PacoteTuristico obj) throws SQLException {
        String sql = "UPDATE pacote_turistico SET nome = ?, descricao = ?, valor = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getDescricao());
        stmt.setDouble(3, obj.getValor());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM pacote_turistico WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private PacoteTuristico mapear(ResultSet rs) throws SQLException {
        PacoteTuristico obj = new PacoteTuristico();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setDescricao(rs.getString("descricao"));
        obj.setValor(rs.getDouble("valor"));
        return obj;
    }
}
