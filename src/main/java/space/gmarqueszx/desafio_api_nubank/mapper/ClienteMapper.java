package space.gmarqueszx.desafio_api_nubank.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ClienteRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ClienteResponse;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = ContatoMapper.class)
public interface ClienteMapper {
    @Mapping(target = "contatos", source = "contatos")
    ClienteResponse toDto (ClienteEntity entity);
    ClienteEntity toEntity (ClienteRequest request);
    List<ClienteResponse> toCollectionDto (List<ClienteEntity> entityList);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ClienteRequest request, @MappingTarget ClienteEntity entity);

    @AfterMapping
    default void vincularOsFilhosAoPai(@MappingTarget ClienteEntity entity) {
        if (entity.getContatos() != null) {
            entity.getContatos().forEach(contato -> contato.setCliente(entity));
        }
    }
}
