package dao;

import entity.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "medicamento".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class MedicamentoDAO {

    private final Connection conexao;

    public MedicamentoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Medicamento inserir(Medicamento obj) throws SQLException {
        String sql = "INSERT INTO medicamento (nome, principio_ativo, estoque_minimo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getPrincipioAtivo());
        stmt.setInt(3, obj.getEstoqueMinimo());
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
    public Medicamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE id = ?";
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
    public List<Medicamento> listarTodos() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Medicamento obj) throws SQLException {
        String sql = "UPDATE medicamento SET nome = ?, principio_ativo = ?, estoque_minimo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getPrincipioAtivo());
        stmt.setInt(3, obj.getEstoqueMinimo());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM medicamento WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Medicamento mapear(ResultSet rs) throws SQLException {
        Medicamento obj = new Medicamento();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setPrincipioAtivo(rs.getString("principio_ativo"));
        obj.setEstoqueMinimo(rs.getInt("estoque_minimo"));
        return obj;
    }
}
