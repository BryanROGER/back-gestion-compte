package fr.bryan_roger.gestionCompte.income;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IncomeService {

    private static final Logger log = LoggerFactory.getLogger(IncomeService.class);

    @Autowired
    private IncomeRepository incomeRepository;


    public ResponseAPI<List<Income>> getAllIncomes() {
        var incomes = incomeRepository.findAll();
        return ResponseApiService.createInstance("200", "Tous les revenus ont été chargé avec succès", incomes);
    }

    public ResponseAPI<Income> getIncomeById(String id) {
        try {
            // s'assurer que l'id est un long
            var idIncome = Long.parseLong(id);
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
        if (income.getId() == 0) {
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

            return ResponseApiService.createInstance("202", "Le revenu à été mis à jour", income);

        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun revenu trouvé à l'ID transmis : " + income.getId(), null);
        } catch (Exception e) {
            return ResponseApiService.createInstance("402", "Aucun revenu trouvé à l'ID transmis : " + income.getId(), null);
        }
    }

    public ResponseAPI<Income> deleteIncome(String id) {
        try {
            // s'assurer que l'id est un long
            var idIncome = Long.parseLong(id);
            // vérifier si un revenu avec l'id demandé existe
            incomeRepository.deleteById(idIncome);
            return ResponseApiService.createInstance("204", "L'utilisateur a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + id, null);
        }
    }

}
