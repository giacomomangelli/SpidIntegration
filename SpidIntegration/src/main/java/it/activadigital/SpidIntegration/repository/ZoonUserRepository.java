package it.activadigital.SpidIntegration.repository;

import it.activadigital.SpidIntegration.model.ZoonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoonUserRepository extends JpaRepository<ZoonUser, Long> {
    ZoonUser findByUsername(String username);
}
