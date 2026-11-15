package dao;

import entity.Medicao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "medicao".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class MedicaoDAO {

    private final Connection conexao;

    public MedicaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Medicao inserir(Medicao obj) throws SQLException {
        String sql = "INSERT INTO medicao (estacao_id, data, nivel_metros) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getEstacaoId());
        stmt.setObject(2, obj.getData());
        stmt.setDouble(3, obj.getNivelMetros());
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
    public Medicao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medicao WHERE id = ?";
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
    public List<Medicao> listarTodos() throws SQLException {
        List<Medicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicao ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Medicao obj) throws SQLException {
        String sql = "UPDATE medicao SET estacao_id = ?, data = ?, nivel_metros = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getEstacaoId());
        stmt.setObject(2, obj.getData());
        stmt.setDouble(3, obj.getNivelMetros());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM medicao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Medicao mapear(ResultSet rs) throws SQLException {
        Medicao obj = new Medicao();
        obj.setId(rs.getInt("id"));
        obj.setEstacaoId(rs.getInt("estacao_id"));
        obj.setData(rs.getObject("data", LocalDate.class));
        obj.setNivelMetros(rs.getDouble("nivel_metros"));
        return obj;
    }
}
