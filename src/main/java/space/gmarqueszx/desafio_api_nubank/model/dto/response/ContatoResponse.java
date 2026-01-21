package space.gmarqueszx.desafio_api_nubank.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoResponse {
    private Long id;
    private String email;
    private String telefone;
    private Long clienteId;

}
