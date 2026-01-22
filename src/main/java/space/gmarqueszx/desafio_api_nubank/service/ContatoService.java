package space.gmarqueszx.desafio_api_nubank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.gmarqueszx.desafio_api_nubank.exception.ClienteNaoEncontradoException;
import space.gmarqueszx.desafio_api_nubank.mapper.ContatoMapper;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ContatoRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ContatoResponse;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;
import space.gmarqueszx.desafio_api_nubank.model.entity.ContatoEntity;
import space.gmarqueszx.desafio_api_nubank.repository.ClienteRepository;
import space.gmarqueszx.desafio_api_nubank.repository.ContatoRepository;

import java.lang.annotation.Documented;
import java.util.List;

@Service
public class ContatoService {
    private final ContatoRepository repository;
    private final ContatoMapper mapper;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ContatoService(ContatoRepository repository, ContatoMapper mapper, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ContatoResponse criar(ContatoRequest request) {
        ClienteEntity clienteEntity = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException(request.getClienteId()));


        ContatoEntity entity = mapper.toEntity(request);
        entity.setCliente(clienteEntity);

        repository.save(entity);

        return mapper.toDto(entity);
    }

    public List<ContatoResponse> listarContatosPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ClienteNaoEncontradoException(clienteId);
        }

        List<ContatoEntity> contatos = repository.findByClienteId(clienteId);
        return mapper.toCollectionDto(contatos);
    }
}
