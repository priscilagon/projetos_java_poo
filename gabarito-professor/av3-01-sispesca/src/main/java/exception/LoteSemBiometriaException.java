package exception;

/**
 * Exceção de negócio lançada quando se tenta calcular indicadores de um lote
 * (ganho de peso, TCA) sem que exista biometria suficiente registrada.
 * É uma exceção verificada (checked) para forçar o tratamento explícito
 * no menu principal, evidenciando o uso de try/catch obrigatório do projeto.
 */
public class LoteSemBiometriaException extends Exception {
    public LoteSemBiometriaException(String mensagem) {
        super(mensagem);
    }
}
