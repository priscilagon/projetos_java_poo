package dao;

import entity.Viagem;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "viagem".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class ViagemDAO {

    private final Connection conexao;

    public ViagemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Viagem inserir(Viagem obj) throws SQLException {
        String sql = "INSERT INTO viagem (embarcacao_id, rota_id, data_partida) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getEmbarcacaoId());
        stmt.setInt(2, obj.getRotaId());
        stmt.setObject(3, obj.getDataPartida());
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
    public Viagem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM viagem WHERE id = ?";
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
    public List<Viagem> listarTodos() throws SQLException {
        List<Viagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM viagem ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Viagem obj) throws SQLException {
        String sql = "UPDATE viagem SET embarcacao_id = ?, rota_id = ?, data_partida = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getEmbarcacaoId());
        stmt.setInt(2, obj.getRotaId());
        stmt.setObject(3, obj.getDataPartida());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM viagem WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Viagem mapear(ResultSet rs) throws SQLException {
        Viagem obj = new Viagem();
        obj.setId(rs.getInt("id"));
        obj.setEmbarcacaoId(rs.getInt("embarcacao_id"));
        obj.setRotaId(rs.getInt("rota_id"));
        obj.setDataPartida(rs.getObject("data_partida", LocalDateTime.class));
        return obj;
    }
}
