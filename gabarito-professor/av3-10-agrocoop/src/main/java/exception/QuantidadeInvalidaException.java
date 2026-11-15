package exception;

/** Exceção de negócio lançada quando quantidade ou preço informados são inválidos (<= 0). */
public class QuantidadeInvalidaException extends Exception {
    public QuantidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
