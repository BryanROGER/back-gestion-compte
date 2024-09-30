package fr.bryan_roger.gestionCompte.budget;


import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/budget")
@RestController
@CrossOrigin
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Budget>>> budgets() {
        var responseBugdets = budgetService.getAllBudgets();
        return ResponseEntity.ok(responseBugdets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Budget>> getBudget(@PathVariable String id) {
        var responseBudget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(responseBudget);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<Budget>> addBudget(@RequestBody Budget budget) {
        var responseBudgetToUpdate = budgetService.createOrUpdateBudget(budget);
        return ResponseEntity.ok(responseBudgetToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Budget>> deleteBudget(@PathVariable String id) {
        var responseBudgetToDelete = budgetService.deleteBudget(id);
        return ResponseEntity.ok(responseBudgetToDelete);
    }

}
