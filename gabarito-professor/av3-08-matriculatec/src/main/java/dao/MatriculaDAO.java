package dao;

import entity.Matricula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "matricula".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class MatriculaDAO {

    private final Connection conexao;

    public MatriculaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Matricula inserir(Matricula obj) throws SQLException {
        String sql = "INSERT INTO matricula (aluno_id, turma_id, situacao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getAlunoId());
        stmt.setInt(2, obj.getTurmaId());
        stmt.setString(3, obj.getSituacao());
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
    public Matricula buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE id = ?";
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
    public List<Matricula> listarTodos() throws SQLException {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Matricula obj) throws SQLException {
        String sql = "UPDATE matricula SET aluno_id = ?, turma_id = ?, situacao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getAlunoId());
        stmt.setInt(2, obj.getTurmaId());
        stmt.setString(3, obj.getSituacao());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Matricula mapear(ResultSet rs) throws SQLException {
        Matricula obj = new Matricula();
        obj.setId(rs.getInt("id"));
        obj.setAlunoId(rs.getInt("aluno_id"));
        obj.setTurmaId(rs.getInt("turma_id"));
        obj.setSituacao(rs.getString("situacao"));
        return obj;
    }
}
