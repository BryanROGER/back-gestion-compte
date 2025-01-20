package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.dal.BudgetRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BudgetService extends CrudGenericService<Budget, UUID> {


    public BudgetService(BudgetRepository budgetRepository) {
        super( budgetRepository, "budget");
    }

}
