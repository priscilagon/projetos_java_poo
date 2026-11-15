package dao;

import entity.Lote;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "lote".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class LoteDAO {

    private final Connection conexao;

    public LoteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Lote inserir(Lote obj) throws SQLException {
        String sql = "INSERT INTO lote (codigo, medicamento_id, quantidade, validade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getCodigo());
        stmt.setInt(2, obj.getMedicamentoId());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getValidade());
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
    public Lote buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM lote WHERE id = ?";
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
    public List<Lote> listarTodos() throws SQLException {
        List<Lote> lista = new ArrayList<>();
        String sql = "SELECT * FROM lote ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Lote obj) throws SQLException {
        String sql = "UPDATE lote SET codigo = ?, medicamento_id = ?, quantidade = ?, validade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getCodigo());
        stmt.setInt(2, obj.getMedicamentoId());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getValidade());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM lote WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Lote mapear(ResultSet rs) throws SQLException {
        Lote obj = new Lote();
        obj.setId(rs.getInt("id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setMedicamentoId(rs.getInt("medicamento_id"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setValidade(rs.getObject("validade", LocalDate.class));
        return obj;
    }
}
