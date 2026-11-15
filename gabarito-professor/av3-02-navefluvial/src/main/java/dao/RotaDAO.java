package dao;

import entity.Rota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "rota".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class RotaDAO {

    private final Connection conexao;

    public RotaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Rota inserir(Rota obj) throws SQLException {
        String sql = "INSERT INTO rota (origem, destino, distancia_km, preco_base_passagem) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getOrigem());
        stmt.setString(2, obj.getDestino());
        stmt.setDouble(3, obj.getDistanciaKm());
        stmt.setDouble(4, obj.getPrecoBasePassagem());
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
    public Rota buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM rota WHERE id = ?";
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
    public List<Rota> listarTodos() throws SQLException {
        List<Rota> lista = new ArrayList<>();
        String sql = "SELECT * FROM rota ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Rota obj) throws SQLException {
        String sql = "UPDATE rota SET origem = ?, destino = ?, distancia_km = ?, preco_base_passagem = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getOrigem());
        stmt.setString(2, obj.getDestino());
        stmt.setDouble(3, obj.getDistanciaKm());
        stmt.setDouble(4, obj.getPrecoBasePassagem());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM rota WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Rota mapear(ResultSet rs) throws SQLException {
        Rota obj = new Rota();
        obj.setId(rs.getInt("id"));
        obj.setOrigem(rs.getString("origem"));
        obj.setDestino(rs.getString("destino"));
        obj.setDistanciaKm(rs.getDouble("distancia_km"));
        obj.setPrecoBasePassagem(rs.getDouble("preco_base_passagem"));
        return obj;
    }
}
