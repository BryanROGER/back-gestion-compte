package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.income.Income;
import fr.bryan_roger.gestionCompte.income.IncomeService;
import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/income")
@RestController
@CrossOrigin
public class IncomeController {

    private static final Logger log = LoggerFactory.getLogger(IncomeController.class);
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Income>>> incomes(Model model) {
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


}
