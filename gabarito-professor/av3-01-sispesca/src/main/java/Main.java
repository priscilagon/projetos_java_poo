import model.*;
import service.CooperativaService;
import exception.LoteSemBiometriaException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SisPesca — Gestão de Cooperativa de Piscicultura de Tambaqui
 * Classe principal: apresenta o menu em modo texto (CRUD) e delega
 * as regras de negócio à camada de serviço (CooperativaService).
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static CooperativaService service = new CooperativaService();

    public static void main(String[] args) {
        popularDadosDemonstracao(); // dados iniciais para facilitar a apresentação
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarCooperado();
                case 2 -> cadastrarCriadouro();
                case 3 -> cadastrarLote();
                case 4 -> registrarBiometria();
                case 5 -> registrarConsumoRacao();
                case 6 -> listarLotes();
                case 7 -> removerLote();
                case 8 -> service.imprimirRelatorioProdutividade();
                case 0 -> System.out.println("Encerrando o SisPesca...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== SisPesca - Cooperativa de Piscicultura =====");
        System.out.println("1 - Cadastrar cooperado");
        System.out.println("2 - Cadastrar criadouro (tanque-rede ou viveiro)");
        System.out.println("3 - Cadastrar lote de peixes");
        System.out.println("4 - Registrar biometria de um lote");
        System.out.println("5 - Registrar consumo de ração de um lote");
        System.out.println("6 - Listar lotes cadastrados");
        System.out.println("7 - Remover lote");
        System.out.println("8 - Relatório de produtividade (TCA)");
        System.out.println("0 - Sair");
    }

    static void cadastrarCooperado() {
        System.out.print("Nome do cooperado: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Propriedade/Comunidade: ");
        String propriedade = sc.nextLine();
        service.cadastrarCooperado(new Cooperado(nome, cpf, propriedade));
        System.out.println("Cooperado cadastrado com sucesso!");
    }

    static void cadastrarCriadouro() {
        System.out.print("Código do criadouro: ");
        String codigo = sc.nextLine();
        System.out.print("Espécie criada (ex.: Tambaqui): ");
        String especie = sc.nextLine();
        System.out.print("Tipo (1-Tanque-rede / 2-Viveiro): ");
        int tipo = lerInteiro("");
        if (tipo == 1) {
            double volume = lerDouble("Volume do tanque em m³: ");
            service.cadastrarCriadouro(new TanqueRede(codigo, especie, volume));
        } else {
            double area = lerDouble("Área do viveiro em m²: ");
            service.cadastrarCriadouro(new Viveiro(codigo, especie, area));
        }
        System.out.println("Criadouro cadastrado com sucesso!");
    }

    static void cadastrarLote() {
        System.out.print("Identificador do lote: ");
        String id = sc.nextLine();
        System.out.print("CPF do cooperado responsável: ");
        String cpf = sc.nextLine();
        Cooperado cooperado = service.buscarCooperadoPorCpf(cpf);
        if (cooperado == null) {
            System.out.println("Cooperado não encontrado.");
            return;
        }
        System.out.print("Código do criadouro: ");
        String codigoCriadouro = sc.nextLine();
        Criadouro criadouro = service.buscarCriadouroPorCodigo(codigoCriadouro);
        if (criadouro == null) {
            System.out.println("Criadouro não encontrado.");
            return;
        }
        int quantidade = lerInteiro("Quantidade de alevinos: ");
        if (quantidade > criadouro.calcularCapacidadeMaxima()) {
            System.out.println("ATENÇÃO: quantidade de alevinos excede a capacidade máxima do criadouro ("
                    + criadouro.calcularCapacidadeMaxima() + "). Cadastro cancelado.");
            return;
        }
        double pesoInicial = lerDouble("Peso médio inicial dos alevinos (g): ");
        service.cadastrarLote(new LotePeixe(id, cooperado, criadouro, LocalDate.now(), quantidade, pesoInicial));
        System.out.println("Lote cadastrado com sucesso!");
    }

    static void registrarBiometria() {
        System.out.print("Identificador do lote: ");
        String id = sc.nextLine();
        LotePeixe lote = service.buscarLotePorIdentificador(id);
        if (lote == null) {
            System.out.println("Lote não encontrado.");
            return;
        }
        double peso = lerDouble("Peso médio atual (g): ");
        double comprimento = lerDouble("Comprimento médio atual (cm): ");
        lote.adicionarBiometria(new Biometria(LocalDate.now(), peso, comprimento));
        System.out.println("Biometria registrada com sucesso!");
    }

    static void registrarConsumoRacao() {
        System.out.print("Identificador do lote: ");
        String id = sc.nextLine();
        LotePeixe lote = service.buscarLotePorIdentificador(id);
        if (lote == null) {
            System.out.println("Lote não encontrado.");
            return;
        }
        double quilos = lerDouble("Quilos de ração fornecidos: ");
        lote.registrarConsumoRacao(quilos);
        System.out.println("Consumo de ração registrado. Total acumulado: "
                + lote.getRacaoTotalConsumidaKg() + " kg");
    }

    static void listarLotes() {
        System.out.println("\n===== Lotes cadastrados =====");
        if (service.getLotes().isEmpty()) {
            System.out.println("Nenhum lote cadastrado.");
            return;
        }
        for (LotePeixe lote : service.getLotes()) {
            System.out.println("- " + lote);
            try {
                System.out.printf("   Ganho de peso médio: %.2fg%n", lote.calcularGanhoPesoMedioGramas());
            } catch (LoteSemBiometriaException e) {
                System.out.println("   " + e.getMessage());
            }
        }
    }

    static void removerLote() {
        System.out.print("Identificador do lote a remover: ");
        String id = sc.nextLine();
        boolean removido = service.removerLote(id);
        System.out.println(removido ? "Lote removido com sucesso!" : "Lote não encontrado.");
    }

    // ---------- utilitários de leitura segura ----------
    static int lerInteiro(String mensagem) {
        while (true) {
            try {
                if (!mensagem.isEmpty()) System.out.print(mensagem);
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex.: 12.5).");
            }
        }
    }

    // Dados de exemplo para facilitar testes durante a apresentação da AV3
    static void popularDadosDemonstracao() {
        Cooperado joao = new Cooperado("João Batista", "123.456.789-00", "Comunidade Nova Esperança - Rio Preto da Eva");
        service.cadastrarCooperado(joao);
        TanqueRede tanque = new TanqueRede("TR-01", "Tambaqui", 20.0);
        service.cadastrarCriadouro(tanque);
        LotePeixe lote = new LotePeixe("LOTE-2026-01", joao, tanque, LocalDate.now().minusMonths(2), 500, 15.0);
        lote.adicionarBiometria(new Biometria(LocalDate.now(), 450.0, 22.0));
        lote.registrarConsumoRacao(120.0);
        service.cadastrarLote(lote);
    }
}
