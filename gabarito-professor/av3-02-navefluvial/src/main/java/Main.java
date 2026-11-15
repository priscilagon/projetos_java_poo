import model.*;
import service.NavegacaoService;
import exception.CapacidadeExcedidaException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * NaveFluvial — Controle de Frota de Transporte Fluvial de Passageiros.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao NavegacaoService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static NavegacaoService service = new NavegacaoService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarEmbarcacao();
                case 2 -> cadastrarRota();
                case 3 -> cadastrarViagem();
                case 4 -> venderPassagem();
                case 5 -> listarViagens();
                case 6 -> service.imprimirRelatorioOcupacao();
                case 0 -> System.out.println("Encerrando o NaveFluvial...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== NaveFluvial - Frota de Transporte Fluvial =====");
        System.out.println("1 - Cadastrar embarcação");
        System.out.println("2 - Cadastrar rota");
        System.out.println("3 - Cadastrar viagem");
        System.out.println("4 - Vender passagem");
        System.out.println("5 - Listar viagens");
        System.out.println("6 - Relatório de ocupação");
        System.out.println("0 - Sair");
    }

    static void cadastrarEmbarcacao() {
        System.out.print("Nome da embarcação: ");
        String nome = sc.nextLine();
        int redes = lerInteiro("Capacidade de redes: ");
        int camarotes = lerInteiro("Capacidade de camarotes: ");
        service.cadastrarEmbarcacao(new Embarcacao(nome, redes, camarotes));
        System.out.println("Embarcação cadastrada!");
    }

    static void cadastrarRota() {
        System.out.print("Origem: ");
        String origem = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        double distancia = lerDouble("Distância (km): ");
        double preco = lerDouble("Preço base da passagem (R$): ");
        service.cadastrarRota(new Rota(origem, destino, distancia, preco));
        System.out.println("Rota cadastrada!");
    }

    static void cadastrarViagem() {
        System.out.print("Nome da embarcação: ");
        Embarcacao emb = service.buscarEmbarcacao(sc.nextLine());
        if (emb == null) { System.out.println("Embarcação não encontrada."); return; }
        listarRotasComIndice();
        int idxRota = lerInteiro("Índice da rota: ");
        Rota rota = service.buscarRota(idxRota);
        if (rota == null) { System.out.println("Rota não encontrada."); return; }
        service.cadastrarViagem(new Viagem(emb, rota, LocalDateTime.now().plusDays(1)));
        System.out.println("Viagem cadastrada!");
    }

    static void venderPassagem() {
        listarViagensComIndice();
        int idx = lerInteiro("Índice da viagem: ");
        Viagem viagem = service.buscarViagem(idx);
        if (viagem == null) { System.out.println("Viagem não encontrada."); return; }
        System.out.print("Nome do passageiro: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Documento: ");
        String doc = sc.nextLine();
        Passageiro passageiro = new Passageiro(nome, cpf, doc);
        int tipo = lerInteiro("Tipo (1-Rede / 2-Camarote): ");
        Passagem passagem = (tipo == 1)
                ? new PassagemRede(passageiro, viagem.getRota().getPrecoBasePassagem())
                : new PassagemCamarote(passageiro, viagem.getRota().getPrecoBasePassagem());
        try {
            viagem.venderPassagem(passagem);
            System.out.printf("Passagem vendida! Valor: R$ %.2f%n", passagem.calcularValor());
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Não foi possível vender: " + e.getMessage());
        }
    }

    static void listarViagens() {
        System.out.println("\n===== Viagens cadastradas =====");
        List<Viagem> viagens = service.getViagens();
        if (viagens.isEmpty()) { System.out.println("Nenhuma viagem cadastrada."); return; }
        for (Viagem v : viagens) System.out.println("- " + v);
    }

    static void listarViagensComIndice() {
        List<Viagem> viagens = service.getViagens();
        for (int i = 0; i < viagens.size(); i++) System.out.println(i + ") " + viagens.get(i));
    }

    static void listarRotasComIndice() {
        List<Rota> rotas = service.getRotas();
        for (int i = 0; i < rotas.size(); i++) System.out.println(i + ") " + rotas.get(i));
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
                System.out.println("Valor inválido. Digite um número (ex.: 150.0).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        Embarcacao emb = new Embarcacao("Almirante Amazonas", 40, 10);
        service.cadastrarEmbarcacao(emb);
        Rota rota = new Rota("Manaus", "Parintins", 370, 150.0);
        service.cadastrarRota(rota);
        service.cadastrarViagem(new Viagem(emb, rota, LocalDateTime.now().plusDays(2)));
    }
}
