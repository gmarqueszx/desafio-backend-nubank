package space.gmarqueszx.desafio_api_nubank.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoRequest {
    @Email
    private String email;
    private String telefone;
    private Long clienteId;
}
