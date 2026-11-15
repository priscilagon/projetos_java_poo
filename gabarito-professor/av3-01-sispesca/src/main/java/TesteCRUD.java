import entity.Cooperado;
import dao.CooperadoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de teste manual do CRUD gerado para o projeto SisPesca.
 * Execute com o banco de dados já criado (ver scripts em /sql) e o driver
 * JDBC correspondente no classpath.
 *
 * Compilação (exemplo com driver PostgreSQL no mesmo diretório):
 *   javac -encoding UTF-8 -cp .:postgresql-42.7.3.jar $(find . -name "*.java")
 *   java  -cp .:postgresql-42.7.3.jar TesteCRUD
 */
public class TesteCRUD {

    public static void main(String[] args) {
        try (Connection conexao = ConexaoBD.conectar()) {
            CooperadoDAO dao = new CooperadoDAO(conexao);

            System.out.println("===== CREATE =====");
            Cooperado novo = new Cooperado("Teste nome", "Teste cpf", "Teste propriedade");
            dao.inserir(novo);
            System.out.println("Inserido com id: " + novo.getId());

            System.out.println("\n===== READ (todos) =====");
            List<Cooperado> todos = dao.listarTodos();
            todos.forEach(System.out::println);

            System.out.println("\n===== READ (por id) =====");
            Cooperado encontrado = dao.buscarPorId(novo.getId());
            System.out.println(encontrado);

            System.out.println("\n===== UPDATE =====");
            // ajuste algum campo antes de atualizar, ex.: encontrado.setNome("Novo nome");
            dao.atualizar(encontrado);
            System.out.println("Registro " + encontrado.getId() + " atualizado.");

            System.out.println("\n===== DELETE =====");
            boolean removido = dao.deletar(novo.getId());
            System.out.println("Removido: " + removido);

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados: " + e.getMessage());
            System.err.println("Confirme que o banco foi criado (scripts em /sql) e que o");
            System.err.println("driver JDBC do SGBD escolhido está no classpath.");
        }
    }
}
