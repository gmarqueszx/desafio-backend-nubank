package space.gmarqueszx.desafio_api_nubank.mapper;

import org.mapstruct.Mapper;
import space.gmarqueszx.desafio_api_nubank.model.dto.request.ContatoRequest;
import space.gmarqueszx.desafio_api_nubank.model.dto.response.ContatoResponse;
import space.gmarqueszx.desafio_api_nubank.model.entity.ContatoEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContatoMapper {
    ContatoResponse toDto(ContatoEntity entity);
    ContatoEntity toEntity(ContatoRequest request);
    List<ContatoResponse> toCollectionDto(List<ContatoEntity> entityList);
}
