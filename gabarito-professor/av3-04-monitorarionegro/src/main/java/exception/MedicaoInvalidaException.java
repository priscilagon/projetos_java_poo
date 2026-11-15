package exception;

/** Exceção de negócio lançada quando uma medição de nível fora de faixa plausível é informada. */
public class MedicaoInvalidaException extends Exception {
    public MedicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
