package exception;

/** Exceção de negócio lançada ao tentar matricular aluno em turma sem vagas disponíveis. */
public class VagaIndisponivelException extends Exception {
    public VagaIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
