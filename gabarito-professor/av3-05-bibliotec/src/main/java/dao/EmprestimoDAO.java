package dao;

import entity.Emprestimo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "emprestimo".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class EmprestimoDAO {

    private final Connection conexao;

    public EmprestimoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Emprestimo inserir(Emprestimo obj) throws SQLException {
        String sql = "INSERT INTO emprestimo (livro_id, aluno_id, data_emprestimo, data_prevista, data_devolucao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getLivroId());
        stmt.setInt(2, obj.getAlunoId());
        stmt.setObject(3, obj.getDataEmprestimo());
        stmt.setObject(4, obj.getDataPrevista());
        stmt.setObject(5, obj.getDataDevolucao());
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
    public Emprestimo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
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
    public List<Emprestimo> listarTodos() throws SQLException {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Emprestimo obj) throws SQLException {
        String sql = "UPDATE emprestimo SET livro_id = ?, aluno_id = ?, data_emprestimo = ?, data_prevista = ?, data_devolucao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getLivroId());
        stmt.setInt(2, obj.getAlunoId());
        stmt.setObject(3, obj.getDataEmprestimo());
        stmt.setObject(4, obj.getDataPrevista());
        stmt.setObject(5, obj.getDataDevolucao());
            stmt.setInt(6, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Emprestimo mapear(ResultSet rs) throws SQLException {
        Emprestimo obj = new Emprestimo();
        obj.setId(rs.getInt("id"));
        obj.setLivroId(rs.getInt("livro_id"));
        obj.setAlunoId(rs.getInt("aluno_id"));
        obj.setDataEmprestimo(rs.getObject("data_emprestimo", LocalDate.class));
        obj.setDataPrevista(rs.getObject("data_prevista", LocalDate.class));
        obj.setDataDevolucao(rs.getObject("data_devolucao", LocalDate.class));
        return obj;
    }
}
