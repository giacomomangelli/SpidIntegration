package it.activadigital.SpidIntegration.repository.spid;

import it.activadigital.SpidIntegration.model.spid.AuthnRequestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthnRequestRepository extends CrudRepository<AuthnRequestModel, Long> {

    Optional<AuthnRequestModel> findById(Long id);

    List<AuthnRequestModel> findByState(String state);

}
