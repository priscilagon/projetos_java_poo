import model.*;
import service.MatriculaService;
import exception.VagaIndisponivelException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * MatriculaTec — Sistema de Matrícula de Cursos Técnicos do CETAM.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao MatriculaService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static MatriculaService service = new MatriculaService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> matricularAluno();
                case 3 -> listarTurmas();
                case 4 -> alterarSituacaoMatricula();
                case 5 -> service.imprimirRelatorioOcupacao();
                case 0 -> System.out.println("Encerrando o MatriculaTec...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== MatriculaTec - Cursos Técnicos do CETAM =====");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Matricular aluno em turma");
        System.out.println("3 - Listar turmas");
        System.out.println("4 - Alterar situação de matrícula");
        System.out.println("5 - Relatório de ocupação");
        System.out.println("0 - Sair");
    }

    static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        int idade = lerInteiro("Idade: ");
        service.cadastrarAluno(new Aluno(nome, matricula, LocalDate.now().minusYears(idade)));
        System.out.println("Aluno cadastrado!");
    }

    static void matricularAluno() {
        System.out.print("Matrícula do aluno: ");
        Aluno aluno = service.buscarAlunoPorMatricula(sc.nextLine());
        if (aluno == null) { System.out.println("Aluno não encontrado."); return; }
        System.out.print("Código da turma: ");
        Turma turma = service.buscarTurmaPorCodigo(sc.nextLine());
        if (turma == null) { System.out.println("Turma não encontrada."); return; }
        try {
            service.matricularAluno(aluno, turma);
            System.out.println("Matrícula realizada com sucesso!");
        } catch (VagaIndisponivelException e) {
            System.out.println("Erro na matrícula: " + e.getMessage());
        }
    }

    static void listarTurmas() {
        System.out.println("\n===== Turmas =====");
        List<Turma> turmas = service.getTurmas();
        if (turmas.isEmpty()) { System.out.println("Nenhuma turma cadastrada."); return; }
        for (Turma t : turmas) System.out.println("- " + t);
    }

    static void alterarSituacaoMatricula() {
        System.out.println("\n===== Matrículas =====");
        List<Matricula> matriculas = service.getMatriculas();
        for (int i = 0; i < matriculas.size(); i++) System.out.println(i + ") " + matriculas.get(i));
        if (matriculas.isEmpty()) return;
        int idx = lerInteiro("Índice da matrícula: ");
        if (idx < 0 || idx >= matriculas.size()) { System.out.println("Índice inválido."); return; }
        System.out.println("Nova situação (1-ATIVA / 2-TRANCADA / 3-CONCLUIDA / 4-CANCELADA): ");
        int op = lerInteiro("");
        SituacaoMatricula situacao = switch (op) {
            case 2 -> SituacaoMatricula.TRANCADA;
            case 3 -> SituacaoMatricula.CONCLUIDA;
            case 4 -> SituacaoMatricula.CANCELADA;
            default -> SituacaoMatricula.ATIVA;
        };
        service.alterarSituacao(matriculas.get(idx), situacao);
        System.out.println("Situação atualizada!");
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
        Curso curso = new Curso("Técnico em Informática", 1200);
        Modulo modulo1 = new Modulo("Módulo I - Fundamentos");
        modulo1.adicionarDisciplina(new Disciplina("Lógica de Programação", 80));
        modulo1.adicionarDisciplina(new Disciplina("Java POO", 80));
        curso.adicionarModulo(modulo1);
        service.cadastrarCurso(curso);
        service.cadastrarTurma(new Turma("TI-2026-1", modulo1, 30));
        service.cadastrarAluno(new Aluno("Carlos Lima", "2026010", LocalDate.now().minusYears(18)));
    }
}
