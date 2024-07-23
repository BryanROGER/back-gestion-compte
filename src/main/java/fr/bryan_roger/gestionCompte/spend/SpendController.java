package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/spend")
@RestController
@CrossOrigin
public class SpendController {

//    private static final Logger log = LoggerFactory.getLogger(SpendController.class);
    private final SpendService spendService;

    public SpendController(SpendService spendService) {
        this.spendService = spendService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Spend>>> users(Model model) {
        var responseUsers = spendService.getAllSpends();
        System.out.println(responseUsers);
        return ResponseEntity.ok(responseUsers);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseAPI<User>> getUser(@PathVariable String id) {
//        var responseUser = userService.getUserById(id);
//        return ResponseEntity.ok(responseUser);
//    }

//    @PutMapping("/add")
//    public ResponseEntity<ResponseAPI<Spend>> addUser(@RequestBody Spend spend) {
//        var responseSpendToUpdate = spendService.addSpend(spend);
//        return ResponseEntity.ok(responseSpendToUpdate);
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<ResponseAPI<User>> deleteUser(@PathVariable String id) {
//        var responseUserToDelete = userService.deleteUser(id);
//        return ResponseEntity.ok(responseUserToDelete);
//    }
}
