package dao;

import entity.Venda;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "venda".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class VendaDAO {

    private final Connection conexao;

    public VendaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Venda inserir(Venda obj) throws SQLException {
        String sql = "INSERT INTO venda (produto_id, feirante_id, quantidade, data, valor_final) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getProdutoId());
        stmt.setInt(2, obj.getFeiranteId());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
        stmt.setDouble(5, obj.getValorFinal());
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
    public Venda buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM venda WHERE id = ?";
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
    public List<Venda> listarTodos() throws SQLException {
        List<Venda> lista = new ArrayList<>();
        String sql = "SELECT * FROM venda ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Venda obj) throws SQLException {
        String sql = "UPDATE venda SET produto_id = ?, feirante_id = ?, quantidade = ?, data = ?, valor_final = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getProdutoId());
        stmt.setInt(2, obj.getFeiranteId());
        stmt.setInt(3, obj.getQuantidade());
        stmt.setObject(4, obj.getData());
        stmt.setDouble(5, obj.getValorFinal());
            stmt.setInt(6, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM venda WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Venda mapear(ResultSet rs) throws SQLException {
        Venda obj = new Venda();
        obj.setId(rs.getInt("id"));
        obj.setProdutoId(rs.getInt("produto_id"));
        obj.setFeiranteId(rs.getInt("feirante_id"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setData(rs.getObject("data", LocalDate.class));
        obj.setValorFinal(rs.getDouble("valor_final"));
        return obj;
    }
}
