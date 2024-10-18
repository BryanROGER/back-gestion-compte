package fr.bryan_roger.gestionCompte.Household;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeRepository extends JpaRepository<Household, UUID> {
}
