package dao;

import entity.Participacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "participacao".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class ParticipacaoDAO {

    private final Connection conexao;

    public ParticipacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Participacao inserir(Participacao obj) throws SQLException {
        String sql = "INSERT INTO participacao (voluntario_id, acao_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getVoluntarioId());
        stmt.setInt(2, obj.getAcaoId());
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
    public Participacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM participacao WHERE id = ?";
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
    public List<Participacao> listarTodos() throws SQLException {
        List<Participacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM participacao ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Participacao obj) throws SQLException {
        String sql = "UPDATE participacao SET voluntario_id = ?, acao_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getVoluntarioId());
        stmt.setInt(2, obj.getAcaoId());
            stmt.setInt(3, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM participacao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Participacao mapear(ResultSet rs) throws SQLException {
        Participacao obj = new Participacao();
        obj.setId(rs.getInt("id"));
        obj.setVoluntarioId(rs.getInt("voluntario_id"));
        obj.setAcaoId(rs.getInt("acao_id"));
        return obj;
    }
}
