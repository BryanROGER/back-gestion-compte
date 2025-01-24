package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Spend;
import fr.bryan_roger.gestionCompte.dal.HouseholdRepository;
import fr.bryan_roger.gestionCompte.dal.SpendRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.services.FormatDateForDatabase;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SpendService extends CrudGenericService<Spend, UUID> {

    private static final Logger logger = LoggerFactory.getLogger(SpendService.class);

    private final SpendRepository spendRepository;

    private final HouseholdRepository householdRepository;

    private final FormatDateForDatabase formatDateForDatabase;

    public SpendService(SpendRepository spendRepository, HouseholdRepository householdRepository, FormatDateForDatabase formatDateForDatabase) {
        super(spendRepository, "dépense");
        this.spendRepository = spendRepository;
        this.householdRepository = householdRepository;
        this.formatDateForDatabase = formatDateForDatabase;
    }

    public ResponseAPI<List<Spend>> getSpendsInAMonth(String month, String year, String householdId) {
        try {
            final var DATE = formatDateForDatabase.convertDateToCorrectFormatDatabase(month, year);
            var spendsOfMonth = spendRepository.findByDateAndHouseholdId(DATE, UUID.fromString(householdId));
            if (spendsOfMonth.isPresent()) {
                var spends = spendsOfMonth.get();
                return ResponseApiService.createInstance(
                        "200",
                        String.format("Les dépenses du mois de %s ont été chargées avec succès", month),
                        spends);
            }

            return ResponseApiService.createInstance(
                    "204",
                    String.format("Aucune dépense n'est valable à cette date"),
                    null);
        } catch (Exception e) {
            return ResponseApiService.createInstance("401",
                    String.format("Erreur dans la récupération des mois de %s ", month), null);
        }
    }

    public ResponseAPI<Spend> create(Spend spend, UUID householdId) {
        spend.setOrder(new Date().getTime());
        var household = householdRepository.findById(householdId);

        if (household.isPresent()) {

            spend.setHousehold(household.get());
            Spend newSpend = repository.save(spend);
            logger.info("Household : {}", household);
            logger.info("Household associé : {}", spend.getHousehold());

            return ResponseApiService.createInstance("201", "Le " + entityName + " a été créé avec succès", newSpend);
        }

        return ResponseApiService.createInstance("406", "Le foyer n'est pas le bon", null);
    }

}
