package fr.bryan_roger.gestionCompte.controller;

import fr.bryan_roger.gestionCompte.bo.Spend;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.bll.SpendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        var responseSpends = spendService.getAll();
        return ResponseEntity.ok(responseSpends);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Spend>> getSpend(@PathVariable String id) {
        var responseSpend = spendService.getById(UUID.fromString(id));
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

    @PostMapping("")
    public ResponseEntity<ResponseAPI<Spend>> addSpend(@RequestBody Spend spend) {
        var responseSpendToAdd = spendService.create(spend);
        return ResponseEntity.ok(responseSpendToAdd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI<Spend>> updateSpend(@RequestBody Spend spend, @PathVariable String id) {
        var responseSpendToUpdate = spendService.update(UUID.fromString(id),spend);
        return ResponseEntity.ok(responseSpendToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI<Spend>> deleteSpend(@PathVariable String id) {
        var responseSpendToDelete = spendService.delete(UUID.fromString(id));
        return ResponseEntity.ok(responseSpendToDelete);
    }
}
