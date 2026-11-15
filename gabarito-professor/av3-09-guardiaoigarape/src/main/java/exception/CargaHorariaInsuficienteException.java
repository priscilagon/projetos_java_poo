package exception;

/** Exceção de negócio lançada ao tentar emitir certificado sem carga horária mínima cumprida. */
public class CargaHorariaInsuficienteException extends Exception {
    public CargaHorariaInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
