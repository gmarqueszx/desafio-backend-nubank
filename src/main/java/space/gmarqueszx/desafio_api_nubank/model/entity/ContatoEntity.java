package space.gmarqueszx.desafio_api_nubank.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "contatos")
public class ContatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Email(message = "o email deve seguir o formato: teste@dominio.com")
    private String email;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private ClienteEntity cliente;
}
