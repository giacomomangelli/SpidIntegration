package it.activadigital.SpidIntegration.repository;

import it.activadigital.SpidIntegration.model.SpidUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<SpidUser, Long> {
}
