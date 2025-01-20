package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
}
