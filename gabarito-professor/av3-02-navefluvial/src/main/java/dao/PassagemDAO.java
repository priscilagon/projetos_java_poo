package dao;

import entity.Passagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "passagem".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class PassagemDAO {

    private final Connection conexao;

    public PassagemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public Passagem inserir(Passagem obj) throws SQLException {
        String sql = "INSERT INTO passagem (viagem_id, tipo, passageiro_nome, passageiro_cpf, passageiro_documento, valor) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, obj.getViagemId());
        stmt.setString(2, obj.getTipo());
        stmt.setString(3, obj.getPassageiroNome());
        stmt.setString(4, obj.getPassageiroCpf());
        stmt.setString(5, obj.getPassageiroDocumento());
        stmt.setDouble(6, obj.getValor());
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
    public Passagem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM passagem WHERE id = ?";
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
    public List<Passagem> listarTodos() throws SQLException {
        List<Passagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM passagem ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(Passagem obj) throws SQLException {
        String sql = "UPDATE passagem SET viagem_id = ?, tipo = ?, passageiro_nome = ?, passageiro_cpf = ?, passageiro_documento = ?, valor = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, obj.getViagemId());
        stmt.setString(2, obj.getTipo());
        stmt.setString(3, obj.getPassageiroNome());
        stmt.setString(4, obj.getPassageiroCpf());
        stmt.setString(5, obj.getPassageiroDocumento());
        stmt.setDouble(6, obj.getValor());
            stmt.setInt(7, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM passagem WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Passagem mapear(ResultSet rs) throws SQLException {
        Passagem obj = new Passagem();
        obj.setId(rs.getInt("id"));
        obj.setViagemId(rs.getInt("viagem_id"));
        obj.setTipo(rs.getString("tipo"));
        obj.setPassageiroNome(rs.getString("passageiro_nome"));
        obj.setPassageiroCpf(rs.getString("passageiro_cpf"));
        obj.setPassageiroDocumento(rs.getString("passageiro_documento"));
        obj.setValor(rs.getDouble("valor"));
        return obj;
    }
}
