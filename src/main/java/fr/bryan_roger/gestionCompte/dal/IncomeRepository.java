package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {
    List<Income> findByDate(String date);
}
