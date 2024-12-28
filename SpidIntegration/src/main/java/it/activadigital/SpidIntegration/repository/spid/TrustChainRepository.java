package it.activadigital.SpidIntegration.repository.spid;

import it.activadigital.SpidIntegration.model.spid.TrustChainModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrustChainRepository extends CrudRepository<TrustChainModel, Long> {

    Optional<TrustChainModel> findById(Long id);

    @Query(
            value = "SELECT * FROM trust_chain tc WHERE tc.sub = ?1 LIMIT 1",
            nativeQuery = true
    )
    TrustChainModel fetchBySub(String sub);

    @Query(
            value =
                    "SELECT tc.* FROM trust_chain tc " +
                            " INNER JOIN fetched_entity_statement fes ON (" +
                            " fes.id = tc.trust_anchor_id AND fes.sub = ?2)" +
                            " WHERE tc.sub = ?1 " +
                            " LIMIT 1",
            nativeQuery = true
    )
    TrustChainModel fetchBySub_TASub(String sub, String trustAnchorSub);

    @Query(
            value =
                    "SELECT tc.* FROM trust_chain tc " +
                            " INNER JOIN fetched_entity_statement fes ON (" +
                            " fes.id = tc.trust_anchor_id AND fes.sub = ?2)" +
                            " WHERE tc.sub = ?1 AND tc.type_ = ?3 AND tc.is_active = 1" +
                            " LIMIT 1",
            nativeQuery = true
    )
    TrustChainModel fetchBySub_TASub_T(String sub, String trustAnchorSub, String type);

}
