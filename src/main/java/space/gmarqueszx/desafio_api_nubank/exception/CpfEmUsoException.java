package space.gmarqueszx.desafio_api_nubank.exception;

public class CpfEmUsoException extends RuntimeException {
    public CpfEmUsoException(String cpf) {
        super("O CPF informado, já possuí cadastro em nossa base de dados.");
    }
}
