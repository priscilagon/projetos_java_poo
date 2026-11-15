import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária responsável por abrir a conexão JDBC com o banco de dados.
 * Suporta PostgreSQL e MySQL — basta trocar o valor de SGBD_ATIVO.
 *
 * Pré-requisitos (fora do JDK, adicionar ao classpath):
 *   - PostgreSQL: driver "org.postgresql:postgresql" (postgresql-42.x.jar)
 *   - MySQL:      driver "com.mysql:mysql-connector-j" (mysql-connector-j-8.x.jar)
 *
 * Exemplo de uso:
 *   try (Connection conexao = ConexaoBD.conectar()) {
 *       ClienteDAO dao = new ClienteDAO(conexao);
 *       ...
 *   }
 */
public class ConexaoBD {

    /** Enumeração dos bancos suportados. */
    public enum SGBD { POSTGRESQL, MYSQL }

    // ------------------------------------------------------------------
    // AJUSTE AQUI conforme o banco de dados que for utilizar:
    private static final SGBD SGBD_ATIVO = SGBD.POSTGRESQL;

    private static final String HOST = "localhost";
    private static final String PORTA_POSTGRES = "5432";
    private static final String PORTA_MYSQL = "3306";
    private static final String NOME_BANCO = "cetam_projeto"; // troque pelo nome do banco de cada projeto
    private static final String USUARIO = "postgres";         // ajuste conforme seu ambiente
    private static final String SENHA = "postgres";           // ajuste conforme seu ambiente
    // ------------------------------------------------------------------

    private ConexaoBD() {
        // classe utilitária: não deve ser instanciada
    }

    public static Connection conectar() throws SQLException {
        String url;
        String usuario = USUARIO;
        String senha = SENHA;

        switch (SGBD_ATIVO) {
            case POSTGRESQL -> {
                url = "jdbc:postgresql://" + HOST + ":" + PORTA_POSTGRES + "/" + NOME_BANCO;
                carregarDriver("org.postgresql.Driver");
            }
            case MYSQL -> {
                url = "jdbc:mysql://" + HOST + ":" + PORTA_MYSQL + "/" + NOME_BANCO
                        + "?useSSL=false&serverTimezone=America/Manaus";
                carregarDriver("com.mysql.cj.jdbc.Driver");
            }
            default -> throw new IllegalStateException("SGBD não suportado: " + SGBD_ATIVO);
        }

        return DriverManager.getConnection(url, usuario, senha);
    }

    private static void carregarDriver(String classeDriver) {
        try {
            Class.forName(classeDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "Driver JDBC não encontrado no classpath: " + classeDriver
                            + ". Adicione o .jar do driver ao projeto.", e);
        }
    }
}
