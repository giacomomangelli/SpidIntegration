package it.activadigital.SpidIntegration.repository;

import it.activadigital.SpidIntegration.model.spid.AuthRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRequestRepository extends CrudRepository<AuthRequest, Long> {
}
