import model.*;
import service.VoluntariadoService;
import exception.CargaHorariaInsuficienteException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * GuardiãoIgarapé — Gestão de Voluntariado Ambiental.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao VoluntariadoService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static VoluntariadoService service = new VoluntariadoService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarVoluntario();
                case 2 -> cadastrarAcao();
                case 3 -> registrarParticipacao();
                case 4 -> consultarHoras();
                case 5 -> emitirCertificado();
                case 6 -> service.imprimirRelatorioPorTipo();
                case 0 -> System.out.println("Encerrando o GuardiãoIgarapé...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== GuardiãoIgarapé - Voluntariado Ambiental =====");
        System.out.println("1 - Cadastrar voluntário");
        System.out.println("2 - Cadastrar ação ambiental");
        System.out.println("3 - Registrar participação");
        System.out.println("4 - Consultar total de horas de um voluntário");
        System.out.println("5 - Emitir certificado");
        System.out.println("6 - Relatório de ações por tipo");
        System.out.println("0 - Sair");
    }

    static void cadastrarVoluntario() {
        System.out.print("Nome do voluntário: ");
        String nome = sc.nextLine();
        System.out.print("Contato: ");
        String contato = sc.nextLine();
        service.cadastrarVoluntario(new Voluntario(nome, contato));
        System.out.println("Voluntário cadastrado!");
    }

    static void cadastrarAcao() {
        System.out.print("Local (ex.: Igarapé do Quarenta): ");
        String local = sc.nextLine();
        double duracao = lerDouble("Duração (horas): ");
        int tipo = lerInteiro("Tipo (1-Limpeza / 2-Plantio / 3-Educação Ambiental): ");
        AcaoAmbiental acao = switch (tipo) {
            case 2 -> new Plantio(local, LocalDate.now(), duracao);
            case 3 -> new EducacaoAmbiental(local, LocalDate.now(), duracao);
            default -> new Limpeza(local, LocalDate.now(), duracao);
        };
        service.cadastrarAcao(acao);
        System.out.println("Ação cadastrada!");
    }

    static void registrarParticipacao() {
        System.out.print("Nome do voluntário: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(sc.nextLine());
        if (voluntario == null) { System.out.println("Voluntário não encontrado."); return; }
        List<AcaoAmbiental> acoes = service.getAcoes();
        for (int i = 0; i < acoes.size(); i++) System.out.println(i + ") " + acoes.get(i));
        int idx = lerInteiro("Índice da ação: ");
        if (idx < 0 || idx >= acoes.size()) { System.out.println("Ação inválida."); return; }
        service.registrarParticipacao(voluntario, acoes.get(idx));
        System.out.println("Participação registrada!");
    }

    static void consultarHoras() {
        System.out.print("Nome do voluntário: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(sc.nextLine());
        if (voluntario == null) { System.out.println("Voluntário não encontrado."); return; }
        System.out.println("Total de horas: " + service.calcularTotalHoras(voluntario) + "h");
    }

    static void emitirCertificado() {
        System.out.print("Nome do voluntário: ");
        Voluntario voluntario = service.buscarVoluntarioPorNome(sc.nextLine());
        if (voluntario == null) { System.out.println("Voluntário não encontrado."); return; }
        try {
            System.out.println("\n" + service.emitirCertificado(voluntario));
        } catch (CargaHorariaInsuficienteException e) {
            System.out.println("Não foi possível emitir: " + e.getMessage());
        }
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
                System.out.println("Valor inválido. Digite um número (ex.: 3.5).");
            }
        }
    }

    static void popularDadosDemonstracao() {
        Voluntario voluntario = new Voluntario("Beatriz Nogueira", "beatriz@email.com");
        service.cadastrarVoluntario(voluntario);
        AcaoAmbiental acao = new Limpeza("Igarapé do Quarenta", LocalDate.now(), 4.0);
        service.cadastrarAcao(acao);
        service.registrarParticipacao(voluntario, acao);
    }
}
