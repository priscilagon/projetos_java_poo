package dao;

import entity.Biometria;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "biometria".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class BiometriaDAO {

    private final Connection conexao;

    public BiometriaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Biometria inserir(Biometria obj) throws SQLException {
        String sql = "INSERT INTO biometria (lote_id, data, peso_medio_gramas, comprimento_medio_cm) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getLoteId());
        stmt.setObject(2, obj.getData());
        stmt.setDouble(3, obj.getPesoMedioGramas());
        stmt.setDouble(4, obj.getComprimentoMedioCm());
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
    public Biometria buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM biometria WHERE id = ?";
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
    public List<Biometria> listarTodos() throws SQLException {
        List<Biometria> lista = new ArrayList<>();
        String sql = "SELECT * FROM biometria ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Biometria obj) throws SQLException {
        String sql = "UPDATE biometria SET lote_id = ?, data = ?, peso_medio_gramas = ?, comprimento_medio_cm = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getLoteId());
        stmt.setObject(2, obj.getData());
        stmt.setDouble(3, obj.getPesoMedioGramas());
        stmt.setDouble(4, obj.getComprimentoMedioCm());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM biometria WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Biometria mapear(ResultSet rs) throws SQLException {
        Biometria obj = new Biometria();
        obj.setId(rs.getInt("id"));
        obj.setLoteId(rs.getInt("lote_id"));
        obj.setData(rs.getObject("data", LocalDate.class));
        obj.setPesoMedioGramas(rs.getDouble("peso_medio_gramas"));
        obj.setComprimentoMedioCm(rs.getDouble("comprimento_medio_cm"));
        return obj;
    }
}
