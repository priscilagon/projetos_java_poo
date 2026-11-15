package exception;

/** Exceção de negócio lançada quando a quantidade vendida excede o estoque do produto. */
public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
