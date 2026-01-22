package space.gmarqueszx.desafio_api_nubank.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }

    public ClienteNaoEncontradoException(Long id) {
        this("Não foi encontrado, nenhum cliente de id: " + id + " em nossa base de dados." );
    }
}
