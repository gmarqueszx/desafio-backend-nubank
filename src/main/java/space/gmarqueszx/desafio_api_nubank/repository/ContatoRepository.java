package space.gmarqueszx.desafio_api_nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.gmarqueszx.desafio_api_nubank.model.entity.ClienteEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ClienteEntity, Long> {
}
