package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/spends")
@RestController
@CrossOrigin
public class SpendController {

    private final SpendService spendService;

    public SpendController(SpendService spendService) {
        this.spendService = spendService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<Spend>>> spends() {
        var responseSpends = spendService.getAllSpends();
        return ResponseEntity.ok(responseSpends);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Spend>> getSpend(@PathVariable String id) {
        var responseSpend = spendService.getSpendById(id);
        return ResponseEntity.ok(responseSpend);
    }

    @PostMapping("/all-in-a-month")
    public ResponseEntity<ResponseAPI<List<Spend>>> getSpendInMonth(@RequestBody Map<String, String> params) {
        String month = params.get("month");
        String year = params.get("year");
        String householdId = params.get("householdID");
        var spendInMonth = spendService.getSpendsInAMonth(month, year, householdId);
        return ResponseEntity.ok(spendInMonth);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<Spend>> addSpend(@RequestBody Spend spend) {
        var responseSpendToUpdate = spendService.createOrUpdateSpend(spend);
        return ResponseEntity.ok(responseSpendToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Spend>> deleteSpend(@PathVariable String id) {
        var responseSpendToDelete = spendService.deleteSpend(id);
        return ResponseEntity.ok(responseSpendToDelete);
    }
}
