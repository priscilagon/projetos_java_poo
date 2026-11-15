package dao;

import entity.Embarcacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "embarcacao".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class EmbarcacaoDAO {

    private final Connection conexao;

    public EmbarcacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Embarcacao inserir(Embarcacao obj) throws SQLException {
        String sql = "INSERT INTO embarcacao (nome, capacidade_rede, capacidade_camarote) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getNome());
        stmt.setInt(2, obj.getCapacidadeRede());
        stmt.setInt(3, obj.getCapacidadeCamarote());
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
    public Embarcacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM embarcacao WHERE id = ?";
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
    public List<Embarcacao> listarTodos() throws SQLException {
        List<Embarcacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM embarcacao ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Embarcacao obj) throws SQLException {
        String sql = "UPDATE embarcacao SET nome = ?, capacidade_rede = ?, capacidade_camarote = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getNome());
        stmt.setInt(2, obj.getCapacidadeRede());
        stmt.setInt(3, obj.getCapacidadeCamarote());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM embarcacao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Embarcacao mapear(ResultSet rs) throws SQLException {
        Embarcacao obj = new Embarcacao();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setCapacidadeRede(rs.getInt("capacidade_rede"));
        obj.setCapacidadeCamarote(rs.getInt("capacidade_camarote"));
        return obj;
    }
}
