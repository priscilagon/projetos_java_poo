package dao;

import entity.Hospede;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "hospede".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class HospedeDAO {

    private final Connection conexao;

    public HospedeDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Hospede inserir(Hospede obj) throws SQLException {
        String sql = "INSERT INTO hospede (nome, documento, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getDocumento());
        stmt.setString(3, obj.getTipo());
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
    public Hospede buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM hospede WHERE id = ?";
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
    public List<Hospede> listarTodos() throws SQLException {
        List<Hospede> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospede ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Hospede obj) throws SQLException {
        String sql = "UPDATE hospede SET nome = ?, documento = ?, tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getDocumento());
        stmt.setString(3, obj.getTipo());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM hospede WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Hospede mapear(ResultSet rs) throws SQLException {
        Hospede obj = new Hospede();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setDocumento(rs.getString("documento"));
        obj.setTipo(rs.getString("tipo"));
        return obj;
    }
}
