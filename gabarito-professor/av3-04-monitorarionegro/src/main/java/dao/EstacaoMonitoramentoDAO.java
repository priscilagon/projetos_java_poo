package dao;

import entity.EstacaoMonitoramento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "estacao_monitoramento".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class EstacaoMonitoramentoDAO {

    private final Connection conexao;

    public EstacaoMonitoramentoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public EstacaoMonitoramento inserir(EstacaoMonitoramento obj) throws SQLException {
        String sql = "INSERT INTO estacao_monitoramento (nome, municipio) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getMunicipio());
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
    public EstacaoMonitoramento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estacao_monitoramento WHERE id = ?";
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
    public List<EstacaoMonitoramento> listarTodos() throws SQLException {
        List<EstacaoMonitoramento> lista = new ArrayList<>();
        String sql = "SELECT * FROM estacao_monitoramento ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(EstacaoMonitoramento obj) throws SQLException {
        String sql = "UPDATE estacao_monitoramento SET nome = ?, municipio = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getMunicipio());
            stmt.setInt(3, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM estacao_monitoramento WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private EstacaoMonitoramento mapear(ResultSet rs) throws SQLException {
        EstacaoMonitoramento obj = new EstacaoMonitoramento();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setMunicipio(rs.getString("municipio"));
        return obj;
    }
}
