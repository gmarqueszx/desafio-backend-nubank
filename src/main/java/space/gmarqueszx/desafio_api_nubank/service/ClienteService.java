package space.gmarqueszx.desafio_api_nubank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.gmarqueszx.desafio_api_nubank.exception.ClienteNaoEncontradoException;
import space.gmarqueszx.desafio_api_nubank.exception.CpfEmUsoException;
import space.gmarqueszx.desafio_api_nubank.exception.NegocioException;
import space.gmarqueszx.desafio_api_nubank.mapper.ClienteMapper;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ClienteRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ClienteResponse;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;
import space.gmarqueszx.desafio_api_nubank.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        return gravar(null, request);
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        return gravar(id, request);
    }

    private ClienteResponse gravar(Long id, ClienteRequest request) {
        if (id == null && repository.existsByCpf(request.getCpf())) {
            throw new CpfEmUsoException(request.getCpf());

        }

        ClienteEntity entity = (id != null) ? repository.findById(id).orElseThrow(
                () -> new ClienteNaoEncontradoException(id)) : new ClienteEntity();

        if (id != null && !entity.getCpf().equals(request.getCpf())) {
            throw new NegocioException("Não é permitido alterar o CPF de um cliente " +
                    "existente.");
        }

        mapper.updateEntityFromDto(request, entity);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Transactional
    public List<ClienteResponse> listarTodos () {
        List<ClienteEntity> clientes = repository.findAll();

        clientes.forEach(c -> {
            System.out.println("Cliente: " + c.getNome());
            System.out.println("Qtd Contatos na Entidade: " +
                    (c.getContatos() != null ? c.getContatos().size() : "NULL"));
        });

        return mapper.toCollectionDto(clientes);
    }

    @Transactional
    public ClienteResponse exibirCliente(Long id) {
        ClienteEntity entity =
                repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return mapper.toDto(entity);
    }

    @Transactional
    public void deletar(Long id) {
        ClienteEntity entity =
                repository.findById(id).orElseThrow(() -> new  ClienteNaoEncontradoException(id));
        repository.deleteById(entity.getId());
    }
}
