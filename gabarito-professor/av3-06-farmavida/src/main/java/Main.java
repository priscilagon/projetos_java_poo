import model.*;
import service.FarmaciaService;
import exception.MedicamentoVencidoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * FarmaVida — Controle de Estoque de Farmácia Comunitária.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao FarmaciaService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static FarmaciaService service = new FarmaciaService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarMedicamento();
                case 2 -> cadastrarLote();
                case 3 -> registrarEntrada();
                case 4 -> registrarSaida();
                case 5 -> listarLotes();
                case 6 -> service.imprimirAlertasValidade();
                case 7 -> service.imprimirAlertasEstoqueBaixo();
                case 0 -> System.out.println("Encerrando o FarmaVida...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== FarmaVida - Farmácia Comunitária =====");
        System.out.println("1 - Cadastrar medicamento");
        System.out.println("2 - Cadastrar lote");
        System.out.println("3 - Registrar entrada de estoque");
        System.out.println("4 - Registrar saída (dispensação)");
        System.out.println("5 - Listar lotes");
        System.out.println("6 - Alertas de validade");
        System.out.println("7 - Alertas de estoque baixo");
        System.out.println("0 - Sair");
    }

    static void cadastrarMedicamento() {
        System.out.print("Nome do medicamento: ");
        String nome = sc.nextLine();
        System.out.print("Princípio ativo: ");
        String principio = sc.nextLine();
        int estoqueMinimo = lerInteiro("Estoque mínimo: ");
        service.cadastrarMedicamento(new Medicamento(nome, principio, estoqueMinimo));
        System.out.println("Medicamento cadastrado!");
    }

    static void cadastrarLote() {
        System.out.print("Nome do medicamento: ");
        Medicamento medicamento = service.buscarMedicamentoPorNome(sc.nextLine());
        if (medicamento == null) { System.out.println("Medicamento não encontrado."); return; }
        System.out.print("Código do lote: ");
        String codigo = sc.nextLine();
        int validadeDias = lerInteiro("Validade (dias a partir de hoje): ");
        service.cadastrarLote(new Lote(codigo, medicamento, 0, LocalDate.now().plusDays(validadeDias)));
        System.out.println("Lote cadastrado!");
    }

    static void registrarEntrada() {
        System.out.print("Código do lote: ");
        Lote lote = service.buscarLotePorCodigo(sc.nextLine());
        if (lote == null) { System.out.println("Lote não encontrado."); return; }
        int quantidade = lerInteiro("Quantidade recebida: ");
        service.registrarEntrada(lote, quantidade);
        System.out.println("Entrada registrada!");
    }

    static void registrarSaida() {
        System.out.print("Código do lote: ");
        Lote lote = service.buscarLotePorCodigo(sc.nextLine());
        if (lote == null) { System.out.println("Lote não encontrado."); return; }
        int quantidade = lerInteiro("Quantidade dispensada: ");
        try {
            service.registrarSaida(lote, quantidade);
            System.out.println("Saída registrada!");
        } catch (MedicamentoVencidoException e) {
            System.out.println("Erro na dispensação: " + e.getMessage());
        }
    }

    static void listarLotes() {
        System.out.println("\n===== Lotes cadastrados =====");
        List<Lote> lotes = service.getLotes();
        if (lotes.isEmpty()) { System.out.println("Nenhum lote cadastrado."); return; }
        for (Lote l : lotes) System.out.println("- " + l);
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

    static void popularDadosDemonstracao() {
        Medicamento dipirona = new Medicamento("Dipirona 500mg", "Dipirona Sódica", 20);
        service.cadastrarMedicamento(dipirona);
        Lote lote = new Lote("L001", dipirona, 0, LocalDate.now().plusDays(25));
        service.cadastrarLote(lote);
        service.registrarEntrada(lote, 15);
    }
}
