package space.gmarqueszx.desafio_api_nubank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ContatoRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ContatoResponse;
import space.gmarqueszx.desafio_api_nubank.service.ContatoService;

@RestController
@RequestMapping("api/v1/contatos")
public class ContatoController {
    private final ContatoService service;

    @Autowired
    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoResponse criar(@RequestBody ContatoRequest request) {
        return service.criar(request);
    }
}
