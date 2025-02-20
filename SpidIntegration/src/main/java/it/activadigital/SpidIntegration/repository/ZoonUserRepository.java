package it.activadigital.SpidIntegration.repository;

import it.activadigital.SpidIntegration.model.ZoonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoonUserRepository extends JpaRepository<ZoonUser, Long> {
    Optional<ZoonUser> findByUsername(String username);
    void deleteByUsername(String username);
}
