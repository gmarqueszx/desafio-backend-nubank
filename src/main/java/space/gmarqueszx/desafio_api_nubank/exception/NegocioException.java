package space.gmarqueszx.desafio_api_nubank.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
