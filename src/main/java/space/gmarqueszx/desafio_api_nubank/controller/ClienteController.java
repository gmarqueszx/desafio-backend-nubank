package space.gmarqueszx.desafio_api_nubank.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ClienteRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ClienteResponse;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ContatoResponse;
import space.gmarqueszx.desafio_api_nubank.service.ClienteService;
import space.gmarqueszx.desafio_api_nubank.service.ContatoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    private final ClienteService service;
    private final ContatoService contatoService;

    @Autowired
    public ClienteController(ClienteService service, ContatoService contatoService) {
        this.service = service;
        this.contatoService = contatoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@RequestBody @Valid ClienteRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id,
                                     @RequestBody @Valid ClienteRequest request) {
        return service.atualizar(id, request);
    }

    @GetMapping
    public List<ClienteResponse> listarTodos() {
        return service.listarTodos();
    }


    @GetMapping("/{id}")
    public ClienteResponse exibirCliente(@PathVariable Long id) {
        return service.exibirCliente(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/{id}/contatos")
    public List<ContatoResponse> exibirContatosPorClienteId(@PathVariable Long id) {
        return contatoService.listarContatosPorCliente(id);
    }
}
