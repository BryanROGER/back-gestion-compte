package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Spend;
import fr.bryan_roger.gestionCompte.dal.SpendRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.services.FormatDateForDatabase;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SpendService extends CrudGenericService<Spend, UUID>  {

    private final SpendRepository spendRepository;

    private final FormatDateForDatabase formatDateForDatabase;

    public SpendService(SpendRepository spendRepository, FormatDateForDatabase formatDateForDatabase) {
        super(spendRepository, "dépense");
        this.spendRepository = spendRepository;
        this.formatDateForDatabase = formatDateForDatabase;
    }

    public ResponseAPI<List<Spend>> getSpendsInAMonth(String month, String year, String householdId) {
        try {
            final var DATE = formatDateForDatabase.convertDateToCorrectFormatDatabase(month, year);
            var spendsOfMonth = spendRepository.findByDateAndHouseholdId(DATE, UUID.fromString(householdId));
            System.out.println(spendsOfMonth);
            return ResponseApiService.createInstance("200",String.format("Les dépenses du mois de %s ont été chargées avec succès", month), spendsOfMonth);
        } catch (Exception e) {
            return ResponseApiService.createInstance("401", String.format("Erreur dans la récupération des mois de %s ", month), null);
        }
    }

//    public ResponseAPI<Spend> create(Spend spend) {
//        var spendSize = repository.findAll().size();
//        spend.setOrder(spendSize-1);
//        Spend newSpend = repository.save(spend);
//        return ResponseApiService.createInstance("201", "Le " + entityName + " a été créé avec succès", newSpend);
//    }

}
