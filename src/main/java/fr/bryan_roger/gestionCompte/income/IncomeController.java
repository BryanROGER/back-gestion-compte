package fr.bryan_roger.gestionCompte.income;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        var responseIncomes = incomeService.getAllIncomes();
        return ResponseEntity.ok(responseIncomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Income>> getIncome(@PathVariable String id) {
        var responseIncome = incomeService.getIncomeById(id);
        return ResponseEntity.ok(responseIncome);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<Income>> addIncome(@RequestBody Income income) {
        var responseIncomeToUpdate = incomeService.createOrUpdateIncome(income);
        return ResponseEntity.ok(responseIncomeToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Income>> deleteIncome(@PathVariable String id) {
        var responseIncomeToDelete = incomeService.deleteIncome(id);
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
