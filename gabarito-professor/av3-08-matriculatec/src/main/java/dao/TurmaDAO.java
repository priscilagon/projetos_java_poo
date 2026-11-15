package dao;

import entity.Turma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "turma".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class TurmaDAO {

    private final Connection conexao;

    public TurmaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Turma inserir(Turma obj) throws SQLException {
        String sql = "INSERT INTO turma (modulo_id, codigo, limite_vagas) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getModuloId());
        stmt.setString(2, obj.getCodigo());
        stmt.setInt(3, obj.getLimiteVagas());
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
    public Turma buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM turma WHERE id = ?";
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
    public List<Turma> listarTodos() throws SQLException {
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM turma ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Turma obj) throws SQLException {
        String sql = "UPDATE turma SET modulo_id = ?, codigo = ?, limite_vagas = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getModuloId());
        stmt.setString(2, obj.getCodigo());
        stmt.setInt(3, obj.getLimiteVagas());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM turma WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Turma mapear(ResultSet rs) throws SQLException {
        Turma obj = new Turma();
        obj.setId(rs.getInt("id"));
        obj.setModuloId(rs.getInt("modulo_id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setLimiteVagas(rs.getInt("limite_vagas"));
        return obj;
    }
}
