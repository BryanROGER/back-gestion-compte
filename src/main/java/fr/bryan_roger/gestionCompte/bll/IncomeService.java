package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.bo.Income;
import fr.bryan_roger.gestionCompte.dal.IncomeRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.services.FormatDateForDatabase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IncomeService extends CrudGenericService<Income, UUID>  {


    private final IncomeRepository incomeRepository;
    private final FormatDateForDatabase formatDateForDatabase;

    public IncomeService(IncomeRepository incomeRepository, FormatDateForDatabase formatDateForDatabase) {
        super(incomeRepository, "revenu");
        this.incomeRepository = incomeRepository;
        this.formatDateForDatabase = formatDateForDatabase;
    }


    public ResponseAPI<List<Income>> getIncomesInAMonth(String month, String year) {
        try {
            final var DATE = formatDateForDatabase.convertDateToCorrectFormatDatabase(month, year);
            var incomesOfMonth = incomeRepository.findByDate(DATE);
            return ResponseApiService.createInstance("200", String.format("Les revenus du mois de %s ont été chargées avec succès", month), incomesOfMonth);
        } catch (Exception e) {
            return ResponseApiService.createInstance("401", String.format("Erreur dans la récupération des revenus du mois de %s ", month), null);
        }
    }

}
