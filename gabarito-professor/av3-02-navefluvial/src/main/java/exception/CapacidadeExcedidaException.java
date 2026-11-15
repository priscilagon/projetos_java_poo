package exception;

/**
 * Exceção de negócio lançada quando a venda de uma passagem ultrapassaria
 * a capacidade (rede ou camarote) da embarcação escalada para a viagem.
 */
public class CapacidadeExcedidaException extends Exception {
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
    }
}
