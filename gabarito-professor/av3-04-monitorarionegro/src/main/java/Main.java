import model.*;
import service.MonitoramentoService;
import exception.MedicaoInvalidaException;

import java.util.List;
import java.util.Scanner;

/**
 * MonitoraRioNegro — Monitoramento de Níveis de Cheia e Seca.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao MonitoramentoService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static MonitoramentoService service = new MonitoramentoService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarEstacao();
                case 2 -> registrarMedicao();
                case 3 -> listarEstacoes();
                case 4 -> compararEstacoes();
                case 0 -> System.out.println("Encerrando o MonitoraRioNegro...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== MonitoraRioNegro - Níveis de Cheia e Seca =====");
        System.out.println("1 - Cadastrar estação de monitoramento");
        System.out.println("2 - Registrar medição diária");
        System.out.println("3 - Listar estações e último nível");
        System.out.println("4 - Comparar duas estações");
        System.out.println("0 - Sair");
    }

    static void cadastrarEstacao() {
        System.out.print("Nome da estação: ");
        String nome = sc.nextLine();
        System.out.print("Município: ");
        String municipio = sc.nextLine();
        service.cadastrarEstacao(new EstacaoMonitoramento(nome, municipio, new ClassificadorPortoDeManaus()));
        System.out.println("Estação cadastrada!");
    }

    static void registrarMedicao() {
        System.out.print("Nome da estação: ");
        EstacaoMonitoramento estacao = service.buscarPorNome(sc.nextLine());
        if (estacao == null) { System.out.println("Estação não encontrada."); return; }
        double nivel = lerDouble("Nível medido hoje (m): ");
        try {
            String alerta = service.registrarMedicao(estacao, nivel);
            System.out.println("Medição registrada. Classificação: " + estacao.classificarUltimoNivel());
            if (alerta != null) System.out.println(">>> " + alerta);
        } catch (MedicaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarEstacoes() {
        System.out.println("\n===== Estações cadastradas =====");
        List<EstacaoMonitoramento> estacoes = service.getEstacoes();
        if (estacoes.isEmpty()) { System.out.println("Nenhuma estação cadastrada."); return; }
        for (EstacaoMonitoramento e : estacoes) {
            Medicao ultima = e.getUltimaMedicao();
            System.out.println("- " + e + " -> " + (ultima != null ? ultima + " (" + e.classificarUltimoNivel() + ")" : "sem medições"));
        }
    }

    static void compararEstacoes() {
        System.out.print("Nome da 1ª estação: ");
        EstacaoMonitoramento a = service.buscarPorNome(sc.nextLine());
        System.out.print("Nome da 2ª estação: ");
        EstacaoMonitoramento b = service.buscarPorNome(sc.nextLine());
        if (a == null || b == null) { System.out.println("Uma das estações não foi encontrada."); return; }
        service.compararEstacoes(a, b);
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
                System.out.println("Valor inválido. Digite um número (ex.: 21.5).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        service.cadastrarEstacao(new EstacaoMonitoramento("Porto de Manaus", "Manaus", new ClassificadorPortoDeManaus()));
        service.cadastrarEstacao(new EstacaoMonitoramento("Igarapé do Mindu", "Manaus", new ClassificadorPortoDeManaus()));
    }
}
