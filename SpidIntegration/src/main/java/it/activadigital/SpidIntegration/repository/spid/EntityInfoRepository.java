package it.activadigital.SpidIntegration.repository.spid;

import it.activadigital.SpidIntegration.model.spid.EntityInfoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityInfoRepository extends CrudRepository<EntityInfoModel, Long> {

    Optional<EntityInfoModel> findById(Long id);

    @Query(
            value = "select * from fetched_entity_statement f where f.sub = ?1 and f.iss = ?2 LIMIT 1",
            nativeQuery = true)
    EntityInfoModel fetchEntity(String sub, String iss);

}
