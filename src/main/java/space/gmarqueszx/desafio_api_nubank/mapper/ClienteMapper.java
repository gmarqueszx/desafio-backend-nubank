package space.gmarqueszx.desafio_api_nubank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ClienteRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ClienteResponse;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponse toDto (ClienteEntity entity);
    ClienteEntity toEntity (ClienteRequest request);
    List<ClienteResponse> toCollectionDto (List<ClienteEntity> entityList);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ClienteRequest request, @MappingTarget ClienteEntity entity);
}
