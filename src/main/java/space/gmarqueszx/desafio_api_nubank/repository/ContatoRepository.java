package space.gmarqueszx.desafio_api_nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;
import space.gmarqueszx.desafio_api_nubank.model.entity.ContatoEntity;

import java.lang.annotation.Documented;
import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    List<ContatoEntity> findByClienteId(Long clienteId);
}
