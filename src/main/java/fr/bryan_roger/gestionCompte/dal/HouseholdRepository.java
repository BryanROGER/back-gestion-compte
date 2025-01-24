package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseholdRepository extends JpaRepository<Household, UUID> {
}
