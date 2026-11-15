package exception;

/** Exceção de negócio lançada quando o período de estadia informado é inválido. */
public class PeriodoInvalidoException extends Exception {
    public PeriodoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
