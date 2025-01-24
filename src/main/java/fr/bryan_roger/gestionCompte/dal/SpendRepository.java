package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SpendRepository extends JpaRepository<Spend, UUID> {
//    Optional<List<Spend>> findByDateAndHouseholdId(String date, UUID householdId);

    @Query("SELECT s FROM Spend s WHERE s.date = :date AND s.household.id = :householdId")
    Optional<List<Spend>> findByDateAndHouseholdId(@Param("date") String date, @Param("householdId") UUID householdId);

}
