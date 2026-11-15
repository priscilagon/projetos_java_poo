import model.*;
import service.PousadaService;
import exception.PeriodoInvalidoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * PousadaSelva — Gestão de Reservas de Pousada de Ecoturismo.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao PousadaService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static PousadaService service = new PousadaService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarAcomodacao();
                case 2 -> cadastrarHospede();
                case 3 -> cadastrarPacote();
                case 4 -> criarReserva();
                case 5 -> listarReservas();
                case 0 -> System.out.println("Encerrando o PousadaSelva...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== PousadaSelva - Ecoturismo na Amazônia =====");
        System.out.println("1 - Cadastrar acomodação");
        System.out.println("2 - Cadastrar hóspede");
        System.out.println("3 - Cadastrar pacote de passeio");
        System.out.println("4 - Criar reserva");
        System.out.println("5 - Listar reservas");
        System.out.println("0 - Sair");
    }

    static void cadastrarAcomodacao() {
        System.out.print("Identificação (ex.: Q01): ");
        String id = sc.nextLine();
        int tipo = lerInteiro("Tipo (1-Quarto Padrão / 2-Bangalô / 3-Suíte Flutuante): ");
        Acomodacao acomodacao = switch (tipo) {
            case 2 -> new Bangalo(id);
            case 3 -> new SuiteFlutuante(id);
            default -> new QuartoPadrao(id);
        };
        service.cadastrarAcomodacao(acomodacao);
        System.out.println("Acomodação cadastrada!");
    }

    static void cadastrarHospede() {
        System.out.print("Nome do hóspede: ");
        String nome = sc.nextLine();
        System.out.print("Documento: ");
        String doc = sc.nextLine();
        int tipo = lerInteiro("Origem (1-Nacional / 2-Estrangeiro): ");
        Hospede hospede = (tipo == 2) ? new HospedeEstrangeiro(nome, doc) : new HospedeNacional(nome, doc);
        service.cadastrarHospede(hospede);
        System.out.println("Hóspede cadastrado!");
    }

    static void cadastrarPacote() {
        System.out.print("Nome do pacote: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        double valor = lerDouble("Valor (R$): ");
        service.cadastrarPacote(new PacoteTuristico(nome, descricao, valor));
        System.out.println("Pacote cadastrado!");
    }

    static void criarReserva() {
        System.out.print("Identificação da acomodação: ");
        Acomodacao acomodacao = service.buscarAcomodacao(sc.nextLine());
        if (acomodacao == null) { System.out.println("Acomodação não encontrada."); return; }
        System.out.print("Documento do hóspede: ");
        Hospede hospede = service.buscarHospede(sc.nextLine());
        if (hospede == null) { System.out.println("Hóspede não encontrado."); return; }
        int diasCheckIn = lerInteiro("Check-in daqui a quantos dias: ");
        int duracao = lerInteiro("Duração da estadia (dias): ");
        try {
            LocalDate checkIn = LocalDate.now().plusDays(diasCheckIn);
            LocalDate checkOut = checkIn.plusDays(duracao);
            Reserva reserva = service.criarReserva(acomodacao, hospede, checkIn, checkOut);
            System.out.print("Deseja adicionar um pacote turístico? (nome ou ENTER para pular): ");
            String nomePacote = sc.nextLine();
            if (!nomePacote.isBlank()) {
                PacoteTuristico pacote = service.buscarPacote(nomePacote);
                if (pacote != null) reserva.adicionarPacote(pacote);
            }
            System.out.printf("Reserva criada! Valor total: R$ %.2f%n", reserva.calcularValorTotal());
        } catch (PeriodoInvalidoException e) {
            System.out.println("Erro na reserva: " + e.getMessage());
        }
    }

    static void listarReservas() {
        System.out.println("\n===== Reservas =====");
        List<Reserva> reservas = service.getReservas();
        if (reservas.isEmpty()) { System.out.println("Nenhuma reserva cadastrada."); return; }
        for (Reserva r : reservas) System.out.println("- " + r);
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
                System.out.println("Valor inválido. Digite um número (ex.: 120.0).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        service.cadastrarAcomodacao(new Bangalo("BG01"));
        service.cadastrarHospede(new HospedeEstrangeiro("John Smith", "PASS123456"));
        service.cadastrarPacote(new PacoteTuristico("Encontro das Águas", "Passeio de barco ao encontro dos rios Negro e Solimões", 150.0));
    }
}
