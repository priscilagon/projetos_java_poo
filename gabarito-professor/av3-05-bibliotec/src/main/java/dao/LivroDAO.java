package dao;

import entity.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "livro".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class LivroDAO {

    private final Connection conexao;

    public LivroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Livro inserir(Livro obj) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, categoria, exemplares_total, exemplares_disponiveis, prazo_emprestimo_dias) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAutor());
        stmt.setString(3, obj.getCategoria());
        stmt.setInt(4, obj.getExemplaresTotal());
        stmt.setInt(5, obj.getExemplaresDisponiveis());
        stmt.setInt(6, obj.getPrazoEmprestimoDias());
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
    public Livro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
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
    public List<Livro> listarTodos() throws SQLException {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Livro obj) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, categoria = ?, exemplares_total = ?, exemplares_disponiveis = ?, prazo_emprestimo_dias = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAutor());
        stmt.setString(3, obj.getCategoria());
        stmt.setInt(4, obj.getExemplaresTotal());
        stmt.setInt(5, obj.getExemplaresDisponiveis());
        stmt.setInt(6, obj.getPrazoEmprestimoDias());
            stmt.setInt(7, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Livro mapear(ResultSet rs) throws SQLException {
        Livro obj = new Livro();
        obj.setId(rs.getInt("id"));
        obj.setTitulo(rs.getString("titulo"));
        obj.setAutor(rs.getString("autor"));
        obj.setCategoria(rs.getString("categoria"));
        obj.setExemplaresTotal(rs.getInt("exemplares_total"));
        obj.setExemplaresDisponiveis(rs.getInt("exemplares_disponiveis"));
        obj.setPrazoEmprestimoDias(rs.getInt("prazo_emprestimo_dias"));
        return obj;
    }
}
