import entity.Acomodacao;
import dao.AcomodacaoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de teste manual do CRUD gerado para o projeto PousadaSelva.
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
            AcomodacaoDAO dao = new AcomodacaoDAO(conexao);

            System.out.println("===== CREATE =====");
            Acomodacao novo = new Acomodacao("Teste identificacao", "Teste tipo", 10.0);
            dao.inserir(novo);
            System.out.println("Inserido com id: " + novo.getId());

            System.out.println("\n===== READ (todos) =====");
            List<Acomodacao> todos = dao.listarTodos();
            todos.forEach(System.out::println);

            System.out.println("\n===== READ (por id) =====");
            Acomodacao encontrado = dao.buscarPorId(novo.getId());
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
