package dao;

import entity.Acomodacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "acomodacao".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class AcomodacaoDAO {

    private final Connection conexao;

    public AcomodacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Acomodacao inserir(Acomodacao obj) throws SQLException {
        String sql = "INSERT INTO acomodacao (identificacao, tipo, valor_diaria) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getIdentificacao());
        stmt.setString(2, obj.getTipo());
        stmt.setDouble(3, obj.getValorDiaria());
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
    public Acomodacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM acomodacao WHERE id = ?";
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
    public List<Acomodacao> listarTodos() throws SQLException {
        List<Acomodacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM acomodacao ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Acomodacao obj) throws SQLException {
        String sql = "UPDATE acomodacao SET identificacao = ?, tipo = ?, valor_diaria = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getIdentificacao());
        stmt.setString(2, obj.getTipo());
        stmt.setDouble(3, obj.getValorDiaria());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM acomodacao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Acomodacao mapear(ResultSet rs) throws SQLException {
        Acomodacao obj = new Acomodacao();
        obj.setId(rs.getInt("id"));
        obj.setIdentificacao(rs.getString("identificacao"));
        obj.setTipo(rs.getString("tipo"));
        obj.setValorDiaria(rs.getDouble("valor_diaria"));
        return obj;
    }
}
