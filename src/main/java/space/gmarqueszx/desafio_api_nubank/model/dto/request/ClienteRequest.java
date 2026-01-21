package space.gmarqueszx.desafio_api_nubank.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ClienteRequest {
    @NotBlank(message = "o nome é obrigatório")
    private String nome;

    @CPF(message = "o cpf deve seguir o formato: 000.000.000-01")
    private String cpf;

    Set<ContatoRequest> contatos = new HashSet<>();

}
