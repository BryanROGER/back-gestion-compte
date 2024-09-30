package fr.bryan_roger.gestionCompte.income;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {
    List<Income> findByDate(String date);
}
