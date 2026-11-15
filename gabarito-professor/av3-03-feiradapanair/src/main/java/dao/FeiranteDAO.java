package dao;

import entity.Feirante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "feirante".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class FeiranteDAO {

    private final Connection conexao;

    public FeiranteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Feirante inserir(Feirante obj) throws SQLException {
        String sql = "INSERT INTO feirante (nome, cpf, banca_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getCpf());
        stmt.setInt(3, obj.getBancaId());
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
    public Feirante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM feirante WHERE id = ?";
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
    public List<Feirante> listarTodos() throws SQLException {
        List<Feirante> lista = new ArrayList<>();
        String sql = "SELECT * FROM feirante ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Feirante obj) throws SQLException {
        String sql = "UPDATE feirante SET nome = ?, cpf = ?, banca_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getCpf());
        stmt.setInt(3, obj.getBancaId());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM feirante WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Feirante mapear(ResultSet rs) throws SQLException {
        Feirante obj = new Feirante();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setCpf(rs.getString("cpf"));
        obj.setBancaId(rs.getInt("banca_id"));
        return obj;
    }
}
