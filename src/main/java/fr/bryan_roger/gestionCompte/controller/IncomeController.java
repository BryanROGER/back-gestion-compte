package fr.bryan_roger.gestionCompte.controller;

import fr.bryan_roger.gestionCompte.bo.Income;
import fr.bryan_roger.gestionCompte.bll.IncomeService;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/incomes")
@RestController
@CrossOrigin
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<Income>>> incomes() {
        var responseIncomes = incomeService.getAll();
        return ResponseEntity.ok(responseIncomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Income>> getIncome(@PathVariable String id) {
        var responseIncome = incomeService.getById(UUID.fromString(id));
        return ResponseEntity.ok(responseIncome);
    }

    @PostMapping("")
    public ResponseEntity<ResponseAPI<Income>> addIncome(@RequestBody Income income) {
        var responseIncomeToUpdate = incomeService.create(income);
        return ResponseEntity.ok(responseIncomeToUpdate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI<Income>> addIncome(@RequestBody Income income, @PathVariable String id) {
        var responseIncomeToUpdate = incomeService.update(UUID.fromString(id),income);
        return ResponseEntity.ok(responseIncomeToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI<Income>> deleteIncome(@PathVariable String id) {
        var responseIncomeToDelete = incomeService.delete(UUID.fromString(id));
        return ResponseEntity.ok(responseIncomeToDelete);
    }

    @PostMapping("/all-in-a-month")
    public ResponseEntity<ResponseAPI<List<Income>>> getIncomeInMonth(@RequestBody Map<String, String> params) {
        String month = params.get("month");
        String year = params.get("year");
        var incomeInMonth = incomeService.getIncomesInAMonth(month, year);
        return ResponseEntity.ok(incomeInMonth);
    }


}
