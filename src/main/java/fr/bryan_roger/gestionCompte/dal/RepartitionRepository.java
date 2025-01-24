package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Household;
import fr.bryan_roger.gestionCompte.bo.Repartition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepartitionRepository extends JpaRepository<Repartition, UUID> {
    List<Repartition> findByHousehold(Household household);
}
