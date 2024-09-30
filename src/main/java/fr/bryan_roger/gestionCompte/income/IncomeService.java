package fr.bryan_roger.gestionCompte.income;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import fr.bryan_roger.gestionCompte.services.FormatDateForDatabase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IncomeService {


    private final IncomeRepository incomeRepository;

    private final FormatDateForDatabase formatDateForDatabase;

    public IncomeService(IncomeRepository incomeRepository, FormatDateForDatabase formatDateForDatabase) {
        this.incomeRepository = incomeRepository;
        this.formatDateForDatabase = formatDateForDatabase;
    }

    public ResponseAPI<List<Income>> getAllIncomes() {
        var incomes = incomeRepository.findAll();
        return ResponseApiService.createInstance("200", "Tous les revenus ont été chargé avec succès", incomes);
    }

    public ResponseAPI<Income> getIncomeById(String id) {
        try {
            // s'assurer que l'id est un long
            var idIncome = UUID.fromString(id);
            // vérifier si un revenu avec l'id demandé existe
            var incomeFound = incomeRepository.getReferenceById(idIncome);
            return ResponseApiService.createInstance("200", "Le revenue chargé avec succès", incomeFound);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun revenue trouvé à l'ID transmis : " + id, null);
        }
    }


    public ResponseAPI<Income> createOrUpdateIncome(Income income) {
        // Si id = 0 on create
        if (income.getId() == null || income.getId().toString().isEmpty()) {
            var incomeToCreate = incomeRepository.save(income);
            return ResponseApiService.createInstance("201", "Revenu créé", incomeToCreate);
        }
        // sinon on modifie
        try {
            Income incomeToUpdate = incomeRepository.findById(income.getId()).orElseThrow();
            incomeToUpdate.setUser(income.getUser());
            incomeToUpdate.setDate(income.getDate());
            incomeToUpdate.setTag(income.getTag());
            incomeToUpdate.setAmount(income.getAmount());
            incomeRepository.save(incomeToUpdate);

            return ResponseApiService.createInstance("202", "Le revenu à été mis à jour", incomeToUpdate);

        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun revenu trouvé à l'ID transmis : " + income.getId(), null);
        } catch (Exception e) {
            return ResponseApiService.createInstance("403", "Aucun revenu trouvé à l'ID transmis : " + income.getId(), null);
        }
    }

    public ResponseAPI<Income> deleteIncome(String id) {
        try {
            var idIncome = UUID.fromString(id);
            incomeRepository.deleteById(idIncome);
            return ResponseApiService.createInstance("204", "L'utilisateur a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + id, null);
        }
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
