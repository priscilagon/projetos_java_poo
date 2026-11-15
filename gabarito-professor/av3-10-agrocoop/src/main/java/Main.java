import model.*;
import service.CooperativaAgricolaService;
import exception.QuantidadeInvalidaException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * AgroCoop — Gestão de Cooperativa Agrícola Familiar.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao CooperativaAgricolaService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static CooperativaAgricolaService service = new CooperativaAgricolaService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarProdutor();
                case 2 -> cadastrarProduto();
                case 3 -> registrarEntrega();
                case 4 -> ranking();
                case 5 -> consolidado();
                case 0 -> System.out.println("Encerrando o AgroCoop...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== AgroCoop - Cooperativa Agrícola Familiar =====");
        System.out.println("1 - Cadastrar produtor");
        System.out.println("2 - Cadastrar produto agrícola");
        System.out.println("3 - Registrar entrega de produção");
        System.out.println("4 - Ranking de produtores por volume");
        System.out.println("5 - Consolidado de entregas por produto");
        System.out.println("0 - Sair");
    }

    static void cadastrarProdutor() {
        System.out.print("Nome do produtor: ");
        String nome = sc.nextLine();
        System.out.print("Comunidade: ");
        String comunidade = sc.nextLine();
        System.out.print("Propriedade: ");
        String propriedade = sc.nextLine();
        service.cadastrarProdutor(new Produtor(nome, comunidade, propriedade));
        System.out.println("Produtor cadastrado!");
    }

    static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Unidade de medida (kg/saca/litro): ");
        String unidade = sc.nextLine();
        double preco = lerDouble("Preço de referência: ");
        service.cadastrarProduto(new ProdutoAgricola(nome, unidade, preco));
        System.out.println("Produto cadastrado!");
    }

    static void registrarEntrega() {
        System.out.print("Nome do produtor: ");
        Produtor produtor = service.buscarProdutorPorNome(sc.nextLine());
        if (produtor == null) { System.out.println("Produtor não encontrado."); return; }
        System.out.print("Nome do produto: ");
        ProdutoAgricola produto = service.buscarProdutoPorNome(sc.nextLine());
        if (produto == null) { System.out.println("Produto não encontrado."); return; }
        double quantidade = lerDouble("Quantidade entregue: ");
        int destino = lerInteiro("Destino (1-Mercado comum / 2-Institucional PAA/PNAE): ");
        try {
            service.registrarEntrega(produtor, produto, quantidade, destino == 2);
            System.out.println("Entrega registrada!");
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void ranking() {
        System.out.println("\n===== Ranking de Produtores por Volume =====");
        List<Map.Entry<Produtor, Double>> ranking = service.rankingPorVolume();
        if (ranking.isEmpty()) { System.out.println("Nenhuma entrega registrada."); return; }
        int pos = 1;
        for (Map.Entry<Produtor, Double> entry : ranking) {
            System.out.printf("%d) %s - %.2f unid. - Receita: R$ %.2f%n",
                    pos++, entry.getKey().getNome(), entry.getValue(), service.calcularReceitaPorProdutor(entry.getKey()));
        }
    }

    static void consolidado() {
        System.out.println("\n===== Consolidado por Produto =====");
        Map<String, Double> consolidado = service.consolidarEntregasPorProduto();
        if (consolidado.isEmpty()) { System.out.println("Nenhuma entrega registrada."); return; }
        consolidado.forEach((produto, total) -> System.out.printf("- %s: %.2f%n", produto, total));
    }

    static int lerInteiro(String msg) {
        while (true) {
            try {
                if (!msg.isEmpty()) System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex.: 50.0).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        service.cadastrarProdutor(new Produtor("Raimundo Nonato", "Comunidade São José", "Sítio Boa Vista - Iranduba"));
        service.cadastrarProduto(new ProdutoAgricola("Farinha de mandioca", "saca", 90.0));
    }
}
