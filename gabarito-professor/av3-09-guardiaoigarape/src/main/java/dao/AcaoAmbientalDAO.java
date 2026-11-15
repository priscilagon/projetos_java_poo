package dao;

import entity.AcaoAmbiental;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "acao_ambiental".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class AcaoAmbientalDAO {

    private final Connection conexao;

    public AcaoAmbientalDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public AcaoAmbiental inserir(AcaoAmbiental obj) throws SQLException {
        String sql = "INSERT INTO acao_ambiental (tipo, local, data, duracao_horas) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getTipo());
        stmt.setString(2, obj.getLocal());
        stmt.setObject(3, obj.getData());
        stmt.setDouble(4, obj.getDuracaoHoras());
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
    public AcaoAmbiental buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM acao_ambiental WHERE id = ?";
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
    public List<AcaoAmbiental> listarTodos() throws SQLException {
        List<AcaoAmbiental> lista = new ArrayList<>();
        String sql = "SELECT * FROM acao_ambiental ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(AcaoAmbiental obj) throws SQLException {
        String sql = "UPDATE acao_ambiental SET tipo = ?, local = ?, data = ?, duracao_horas = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getTipo());
        stmt.setString(2, obj.getLocal());
        stmt.setObject(3, obj.getData());
        stmt.setDouble(4, obj.getDuracaoHoras());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM acao_ambiental WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private AcaoAmbiental mapear(ResultSet rs) throws SQLException {
        AcaoAmbiental obj = new AcaoAmbiental();
        obj.setId(rs.getInt("id"));
        obj.setTipo(rs.getString("tipo"));
        obj.setLocal(rs.getString("local"));
        obj.setData(rs.getObject("data", LocalDate.class));
        obj.setDuracaoHoras(rs.getDouble("duracao_horas"));
        return obj;
    }
}
