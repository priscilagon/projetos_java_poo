package dao;

import entity.MovimentacaoEstoque;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "movimentacao_estoque".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class MovimentacaoEstoqueDAO {

    private final Connection conexao;

    public MovimentacaoEstoqueDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public MovimentacaoEstoque inserir(MovimentacaoEstoque obj) throws SQLException {
        String sql = "INSERT INTO movimentacao_estoque (lote_id, tipo, quantidade, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getLoteId());
        stmt.setString(2, obj.getTipo());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
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
    public MovimentacaoEstoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM movimentacao_estoque WHERE id = ?";
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
    public List<MovimentacaoEstoque> listarTodos() throws SQLException {
        List<MovimentacaoEstoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao_estoque ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(MovimentacaoEstoque obj) throws SQLException {
        String sql = "UPDATE movimentacao_estoque SET lote_id = ?, tipo = ?, quantidade = ?, data = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getLoteId());
        stmt.setString(2, obj.getTipo());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM movimentacao_estoque WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private MovimentacaoEstoque mapear(ResultSet rs) throws SQLException {
        MovimentacaoEstoque obj = new MovimentacaoEstoque();
        obj.setId(rs.getInt("id"));
        obj.setLoteId(rs.getInt("lote_id"));
        obj.setTipo(rs.getString("tipo"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setData(rs.getObject("data", LocalDate.class));
        return obj;
    }
}
