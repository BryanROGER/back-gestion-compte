package fr.bryan_roger.gestionCompte.controller;


import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.bll.BudgetService;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/budgets")
@RestController
@CrossOrigin
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<Budget>>> budgets() {
        var responseBugdets = budgetService.getAll();
        return ResponseEntity.ok(responseBugdets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Budget>> getBudget(@PathVariable String id) {
        var responseBudget = budgetService.getById(UUID.fromString(id));
        return ResponseEntity.ok(responseBudget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI<Budget>> updateBudget(@RequestBody Budget budget, @PathVariable String id) {
        var responseBudgetToUpdate = budgetService.update(UUID.fromString(id), budget);
        return ResponseEntity.ok(responseBudgetToUpdate);
    }

    @PostMapping("")
    public ResponseEntity<ResponseAPI<Budget>> addBudget(@RequestBody Budget budget) {
        var responseBudget = budgetService.create(budget);
        return ResponseEntity.ok(responseBudget);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Budget>> deleteBudget(@PathVariable String id) {
        var responseBudgetToDelete = budgetService.delete(UUID.fromString(id));
        return ResponseEntity.ok(responseBudgetToDelete);
    }

}
