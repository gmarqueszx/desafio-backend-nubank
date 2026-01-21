package space.gmarqueszx.desafio_api_nubank.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClienteResponse {
    private Long id;
    private String nome;
    private Set<ContatoResponse> contatos;
}
