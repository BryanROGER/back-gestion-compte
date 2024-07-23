package fr.bryan_roger.gestionCompte.budget;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public ResponseAPI<List<Budget>> getAllBudgets() {
        var budgets = budgetRepository.findAll();
        return ResponseApiService.createInstance("200", "Les budgets ont été chargés avec succès", budgets);
    }

    public ResponseAPI<Budget> getBudgetById(String id) {
        var idBudget = Long.parseLong(id);
        var budget = budgetRepository.getReferenceById(idBudget);
        return ResponseApiService.createInstance("200", "Le budget a été chargé avec succès", budget);
    }

    public ResponseAPI<Budget> createOrUpdateBudget(Budget budget) {
        if (budget.getId() == 0) {
            var newBudget = budgetRepository.save(budget);
            return ResponseApiService.createInstance("201", "Le budget a été créé avec succès", newBudget);
        }

        var budgetToUpdate = budgetRepository.getReferenceById(budget.getId());
        budgetToUpdate.setAmount(budget.getAmount());
        budgetToUpdate.setTag(budget.getTag());
        budgetToUpdate = budgetRepository.save(budgetToUpdate);
        return ResponseApiService.createInstance("202","Le budget a été mis à jour avec succès",budgetToUpdate);
    }

    public ResponseAPI<Budget> deleteBudget(String id) {
        try {
            // s'assurer que l'id est un long
            var idBudget = Long.parseLong(id);
            // vérifier si un user avec l'id demandé existe
            budgetRepository.deleteById(idBudget);
            return ResponseApiService.createInstance("204", "Le budget a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun budget trouvé à l'ID transmis : " + id, null);
        }
    }
}
