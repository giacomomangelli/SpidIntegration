package it.activadigital.SpidIntegration.repository.spid;

import it.activadigital.SpidIntegration.model.spid.AuthnTokenModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthnTokenRepository extends CrudRepository<AuthnTokenModel, Long> {

    Optional<AuthnTokenModel> findById(Long id);

    @Query(
            value =
                    "select * from oidc_authentication_token o " +
                            " where o.user_key = ?1 and revoked is null " +
                            " order by modified",
            nativeQuery = true
    )
    List<AuthnTokenModel> findUserTokens(String userKey);

}
