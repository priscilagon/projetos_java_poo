package dao;

import entity.LotePeixe;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) da tabela "lote_peixe".
 * Implementa o CRUD completo (Create, Read, Update, Delete) via JDBC puro,
 * usando PreparedStatement para evitar SQL Injection.
 * Funciona tanto com PostgreSQL quanto com MySQL — a sintaxe usada aqui
 * (JDBC padrão + SQL ANSI) é compatível com os dois bancos.
 */
public class LotePeixeDAO {

    private final Connection conexao;

    public LotePeixeDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** CREATE - insere um novo registro e retorna o objeto com o id gerado. */
    public LotePeixe inserir(LotePeixe obj) throws SQLException {
        String sql = "INSERT INTO lote_peixe (identificador, cooperado_id, criadouro_id, data_povoamento, quantidade_alevinos, peso_inicial_medio_gramas, racao_total_consumida_kg) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, obj.getIdentificador());
        stmt.setInt(2, obj.getCooperadoId());
        stmt.setInt(3, obj.getCriadouroId());
        stmt.setObject(4, obj.getDataPovoamento());
        stmt.setInt(5, obj.getQuantidadeAlevinos());
        stmt.setDouble(6, obj.getPesoInicialMedioGramas());
        stmt.setDouble(7, obj.getRacaoTotalConsumidaKg());
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
    public LotePeixe buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM lote_peixe WHERE id = ?";
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
    public List<LotePeixe> listarTodos() throws SQLException {
        List<LotePeixe> lista = new ArrayList<>();
        String sql = "SELECT * FROM lote_peixe ORDER BY id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /** UPDATE - atualiza todos os campos de um registro existente (identificado pelo id). */
    public boolean atualizar(LotePeixe obj) throws SQLException {
        String sql = "UPDATE lote_peixe SET identificador = ?, cooperado_id = ?, criadouro_id = ?, data_povoamento = ?, quantidade_alevinos = ?, peso_inicial_medio_gramas = ?, racao_total_consumida_kg = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, obj.getIdentificador());
        stmt.setInt(2, obj.getCooperadoId());
        stmt.setInt(3, obj.getCriadouroId());
        stmt.setObject(4, obj.getDataPovoamento());
        stmt.setInt(5, obj.getQuantidadeAlevinos());
        stmt.setDouble(6, obj.getPesoInicialMedioGramas());
        stmt.setDouble(7, obj.getRacaoTotalConsumidaKg());
            stmt.setInt(8, obj.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /** DELETE - remove um registro pelo id. Retorna true se algum registro foi removido. */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM lote_peixe WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private LotePeixe mapear(ResultSet rs) throws SQLException {
        LotePeixe obj = new LotePeixe();
        obj.setId(rs.getInt("id"));
        obj.setIdentificador(rs.getString("identificador"));
        obj.setCooperadoId(rs.getInt("cooperado_id"));
        obj.setCriadouroId(rs.getInt("criadouro_id"));
        obj.setDataPovoamento(rs.getObject("data_povoamento", LocalDate.class));
        obj.setQuantidadeAlevinos(rs.getInt("quantidade_alevinos"));
        obj.setPesoInicialMedioGramas(rs.getDouble("peso_inicial_medio_gramas"));
        obj.setRacaoTotalConsumidaKg(rs.getDouble("racao_total_consumida_kg"));
        return obj;
    }
}
