package dao;

import entity.Entrega;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "entrega".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class EntregaDAO {

    private final Connection conexao;

    public EntregaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Entrega inserir(Entrega obj) throws SQLException {
        String sql = "INSERT INTO entrega (produtor_id, produto_id, quantidade, data, destino) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getProdutorId());
        stmt.setInt(2, obj.getProdutoId());
        stmt.setDouble(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
        stmt.setString(5, obj.getDestino());
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
    public Entrega buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM entrega WHERE id = ?";
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
    public List<Entrega> listarTodos() throws SQLException {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrega ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Entrega obj) throws SQLException {
        String sql = "UPDATE entrega SET produtor_id = ?, produto_id = ?, quantidade = ?, data = ?, destino = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getProdutorId());
        stmt.setInt(2, obj.getProdutoId());
        stmt.setDouble(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
        stmt.setString(5, obj.getDestino());
            stmt.setInt(6, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM entrega WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Entrega mapear(ResultSet rs) throws SQLException {
        Entrega obj = new Entrega();
        obj.setId(rs.getInt("id"));
        obj.setProdutorId(rs.getInt("produtor_id"));
        obj.setProdutoId(rs.getInt("produto_id"));
        obj.setQuantidade(rs.getDouble("quantidade"));
        obj.setData(rs.getObject("data", LocalDate.class));
        obj.setDestino(rs.getString("destino"));
        return obj;
    }
}
