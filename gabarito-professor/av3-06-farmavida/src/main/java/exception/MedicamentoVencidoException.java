package exception;

/** Exceção de negócio lançada ao tentar dispensar medicamento de lote vencido ou sem quantidade suficiente. */
public class MedicamentoVencidoException extends Exception {
    public MedicamentoVencidoException(String mensagem) {
        super(mensagem);
    }
}
