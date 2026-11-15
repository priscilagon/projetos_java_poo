import model.*;
import service.FeiraService;
import exception.EstoqueInsuficienteException;

import java.util.List;
import java.util.Scanner;

/**
 * FeiraDaPanair — Gestão de Feirantes e Produtos Regionais.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao FeiraService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static FeiraService service = new FeiraService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarFeirante();
                case 2 -> cadastrarProduto();
                case 3 -> registrarVenda();
                case 4 -> listarProdutos();
                case 5 -> relatorioFaturamento();
                case 6 -> relatorioMaisVendidos();
                case 0 -> System.out.println("Encerrando o FeiraDaPanair...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== FeiraDaPanair - Gestão de Feirantes =====");
        System.out.println("1 - Cadastrar feirante");
        System.out.println("2 - Cadastrar produto");
        System.out.println("3 - Registrar venda");
        System.out.println("4 - Listar produtos e estoque");
        System.out.println("5 - Relatório de faturamento");
        System.out.println("6 - Produto(s) mais vendido(s)");
        System.out.println("0 - Sair");
    }

    static void cadastrarFeirante() {
        System.out.print("Nome do feirante: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int numeroBanca = lerInteiro("Número da banca: ");
        System.out.print("Localização da banca: ");
        String local = sc.nextLine();
        service.cadastrarFeirante(new Feirante(nome, cpf, new Banca(numeroBanca, local)));
        System.out.println("Feirante cadastrado!");
    }

    static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        double preco = lerDouble("Preço unitário (R$): ");
        int estoque = lerInteiro("Estoque inicial: ");
        int categoria = lerInteiro("Categoria (1-Fruta / 2-Pescado / 3-Artesanato): ");
        Produto produto = switch (categoria) {
            case 1 -> new ProdutoFruta(nome, preco, estoque);
            case 2 -> new ProdutoPescado(nome, preco, estoque);
            default -> new ProdutoArtesanato(nome, preco, estoque);
        };
        service.cadastrarProduto(produto);
        System.out.println("Produto cadastrado!");
    }

    static void registrarVenda() {
        System.out.print("CPF do feirante: ");
        Feirante feirante = service.buscarFeirantePorCpf(sc.nextLine());
        if (feirante == null) { System.out.println("Feirante não encontrado."); return; }
        System.out.print("Nome do produto: ");
        Produto produto = service.buscarProdutoPorNome(sc.nextLine());
        if (produto == null) { System.out.println("Produto não encontrado."); return; }
        int quantidade = lerInteiro("Quantidade: ");
        try {
            service.registrarVenda(produto, quantidade, feirante);
            System.out.println("Venda registrada com sucesso!");
        } catch (EstoqueInsuficienteException e) {
            System.out.println("Erro na venda: " + e.getMessage());
        }
    }

    static void listarProdutos() {
        System.out.println("\n===== Produtos cadastrados =====");
        List<Produto> produtos = service.getProdutos();
        if (produtos.isEmpty()) { System.out.println("Nenhum produto cadastrado."); return; }
        for (Produto p : produtos) System.out.println("- " + p);
    }

    static void relatorioFaturamento() {
        System.out.printf("%nFaturamento total: R$ %.2f%n", service.calcularFaturamentoTotal());
        for (Feirante f : service.getFeirantes()) {
            System.out.printf("  %s: R$ %.2f%n", f.getNome(), service.calcularFaturamentoPorFeirante(f));
        }
    }

    static void relatorioMaisVendidos() {
        List<String> maisVendidos = service.produtosMaisVendidos();
        System.out.println("\n===== Produto(s) mais vendido(s) =====");
        if (maisVendidos.isEmpty()) { System.out.println("Nenhuma venda registrada."); return; }
        maisVendidos.forEach(p -> System.out.println("- " + p));
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
                System.out.println("Valor inválido. Digite um número (ex.: 12.5).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        Feirante feirante = new Feirante("Maria das Graças", "111.222.333-44", new Banca(12, "Feira da Panair"));
        service.cadastrarFeirante(feirante);
        service.cadastrarProduto(new ProdutoFruta("Açaí (litro)", 12.0, 100));
        service.cadastrarProduto(new ProdutoPescado("Tambaqui (kg)", 22.0, 50));
        service.cadastrarProduto(new ProdutoArtesanato("Cesto de tucumã", 45.0, 15));
    }
}
