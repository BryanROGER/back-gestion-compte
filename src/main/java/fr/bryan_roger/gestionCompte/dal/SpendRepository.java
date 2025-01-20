package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Spend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpendRepository extends JpaRepository<Spend, UUID> {
    List<Spend> findByDateAndHouseholdId(String date, UUID householdId);
}
