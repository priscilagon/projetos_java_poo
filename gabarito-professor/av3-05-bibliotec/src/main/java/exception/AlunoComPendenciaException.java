package exception;

/** Exceção de negócio lançada ao tentar emprestar livro a aluno com pendências (livros em atraso). */
public class AlunoComPendenciaException extends Exception {
    public AlunoComPendenciaException(String mensagem) {
        super(mensagem);
    }
}
