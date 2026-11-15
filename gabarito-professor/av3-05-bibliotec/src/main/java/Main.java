import model.*;
import service.BibliotecaService;
import exception.AlunoComPendenciaException;

import java.util.List;
import java.util.Scanner;

/**
 * BiblioTec — Sistema de Biblioteca do CETAM.
 * Menu em modo texto (CRUD) que delega as regras de negócio ao BibliotecaService.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static BibliotecaService service = new BibliotecaService();

    public static void main(String[] args) {
        popularDadosDemonstracao();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarAluno();
                case 3 -> realizarEmprestimo();
                case 4 -> registrarDevolucao();
                case 5 -> listarAcervo();
                case 6 -> service.imprimirRelatorioAtrasos();
                case 0 -> System.out.println("Encerrando o BiblioTec...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== BiblioTec - Biblioteca do CETAM =====");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Cadastrar aluno");
        System.out.println("3 - Realizar empréstimo");
        System.out.println("4 - Registrar devolução");
        System.out.println("5 - Listar acervo");
        System.out.println("6 - Relatório de atrasos");
        System.out.println("0 - Sair");
    }

    static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Categoria (técnico/literatura/referência): ");
        String categoria = sc.nextLine();
        int exemplares = lerInteiro("Quantidade de exemplares: ");
        int prazo = lerInteiro("Prazo de empréstimo (dias): ");
        service.cadastrarLivro(new Livro(titulo, autor, categoria, exemplares, prazo));
        System.out.println("Livro cadastrado!");
    }

    static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();
        System.out.print("Turma: ");
        String turma = sc.nextLine();
        service.cadastrarAluno(new Aluno(nome, matricula, curso, turma));
        System.out.println("Aluno cadastrado!");
    }

    static void realizarEmprestimo() {
        System.out.print("Título do livro: ");
        Livro livro = service.buscarLivroPorTitulo(sc.nextLine());
        if (livro == null) { System.out.println("Livro não encontrado."); return; }
        System.out.print("Matrícula do aluno: ");
        Aluno aluno = service.buscarAlunoPorMatricula(sc.nextLine());
        if (aluno == null) { System.out.println("Aluno não encontrado."); return; }
        try {
            service.realizarEmprestimo(livro, aluno);
            System.out.println("Empréstimo realizado! Devolução prevista em " + livro.getPrazoEmprestimoDias() + " dia(s).");
        } catch (AlunoComPendenciaException e) {
            System.out.println("Não foi possível emprestar: " + e.getMessage());
        }
    }

    static void registrarDevolucao() {
        System.out.print("Título do livro: ");
        Livro livro = service.buscarLivroPorTitulo(sc.nextLine());
        System.out.print("Matrícula do aluno: ");
        Aluno aluno = service.buscarAlunoPorMatricula(sc.nextLine());
        if (livro == null || aluno == null) { System.out.println("Livro ou aluno não encontrado."); return; }
        Emprestimo emprestimo = service.buscarEmprestimoAtivo(livro, aluno);
        if (emprestimo == null) { System.out.println("Não há empréstimo ativo para esses dados."); return; }
        double multa = emprestimo.devolver();
        System.out.printf("Devolução registrada! Multa por atraso: R$ %.2f%n", multa);
    }

    static void listarAcervo() {
        System.out.println("\n===== Acervo =====");
        List<Livro> acervo = service.getAcervo();
        if (acervo.isEmpty()) { System.out.println("Nenhum livro cadastrado."); return; }
        for (Livro l : acervo) System.out.println("- " + l);
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
        service.cadastrarLivro(new Livro("Java para Iniciantes", "Autor Regional", "técnico", 3, 7));
        service.cadastrarAluno(new Aluno("Ana Souza", "2026001", "Informática", "Turma A"));
    }
}
