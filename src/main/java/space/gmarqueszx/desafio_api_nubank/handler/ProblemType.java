package space.gmarqueszx.desafio_api_nubank.handler;

import lombok.Getter;

@Getter
public enum ProblemType {
    CLIENTE_NAO_ENCONTRADO("/cliente-nao-encontrado", "Cliente não encontrado"),
    CPF_EM_USO("cpf-em-uso", "CPF em uso"),
    NEGOCIO_EXCEPTION("erro-de-negocio", "Erro de negócio"),
    REQUEST_FALHO("/request-falho", "Request falho");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://gmarqueszx.space" + path;
        this.title = title;
    }
}
