package dao;

import entity.ProdutoAgricola;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "produto_agricola".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class ProdutoAgricolaDAO {

    private final Connection conexao;

    public ProdutoAgricolaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public ProdutoAgricola inserir(ProdutoAgricola obj) throws SQLException {
        String sql = "INSERT INTO produto_agricola (nome, unidade_medida, preco_referencia) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getUnidadeMedida());
        stmt.setDouble(3, obj.getPrecoReferencia());
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
    public ProdutoAgricola buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produto_agricola WHERE id = ?";
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
    public List<ProdutoAgricola> listarTodos() throws SQLException {
        List<ProdutoAgricola> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto_agricola ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(ProdutoAgricola obj) throws SQLException {
        String sql = "UPDATE produto_agricola SET nome = ?, unidade_medida = ?, preco_referencia = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getUnidadeMedida());
        stmt.setDouble(3, obj.getPrecoReferencia());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM produto_agricola WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private ProdutoAgricola mapear(ResultSet rs) throws SQLException {
        ProdutoAgricola obj = new ProdutoAgricola();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setUnidadeMedida(rs.getString("unidade_medida"));
        obj.setPrecoReferencia(rs.getDouble("preco_referencia"));
        return obj;
    }
}
