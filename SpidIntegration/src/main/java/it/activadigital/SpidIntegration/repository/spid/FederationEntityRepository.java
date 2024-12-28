package it.activadigital.SpidIntegration.repository.spid;

import it.activadigital.SpidIntegration.model.spid.FederationEntityModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FederationEntityRepository extends CrudRepository<FederationEntityModel, Long> {

    Optional<FederationEntityModel> findById(Long id);

    @Query(
            value = "select * from federation_entity_configuration f where f.sub = ?1 and f.is_active = ?2 LIMIT 1",
            nativeQuery = true
    )
    FederationEntityModel fetchBySubActive(String sub, boolean active);

    @Query(
            value = "select * from federation_entity_configuration f where f.sub = ?1 LIMIT 1",
            nativeQuery = true
    )
    FederationEntityModel fetchBySubject(String subject);

}
