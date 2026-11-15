package dao;

import entity.Criadouro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "criadouro".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class CriadouroDAO {

    private final Connection conexao;

    public CriadouroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Criadouro inserir(Criadouro obj) throws SQLException {
        String sql = "INSERT INTO criadouro (codigo, tipo, especie_criada, volume_m3, area_m2) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getCodigo());
        stmt.setString(2, obj.getTipo());
        stmt.setString(3, obj.getEspecieCriada());
        stmt.setDouble(4, obj.getVolumeM3());
        stmt.setDouble(5, obj.getAreaM2());
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
    public Criadouro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM criadouro WHERE id = ?";
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
    public List<Criadouro> listarTodos() throws SQLException {
        List<Criadouro> lista = new ArrayList<>();
        String sql = "SELECT * FROM criadouro ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Criadouro obj) throws SQLException {
        String sql = "UPDATE criadouro SET codigo = ?, tipo = ?, especie_criada = ?, volume_m3 = ?, area_m2 = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getCodigo());
        stmt.setString(2, obj.getTipo());
        stmt.setString(3, obj.getEspecieCriada());
        stmt.setDouble(4, obj.getVolumeM3());
        stmt.setDouble(5, obj.getAreaM2());
            stmt.setInt(6, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM criadouro WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Criadouro mapear(ResultSet rs) throws SQLException {
        Criadouro obj = new Criadouro();
        obj.setId(rs.getInt("id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setTipo(rs.getString("tipo"));
        obj.setEspecieCriada(rs.getString("especie_criada"));
        obj.setVolumeM3(rs.getDouble("volume_m3"));
        obj.setAreaM2(rs.getDouble("area_m2"));
        return obj;
    }
}
