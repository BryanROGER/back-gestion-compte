package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<User>>> users(Model model) {
        var responseUsers = userService.getAllUsers();
        return ResponseEntity.ok(responseUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<User>> getUser(@PathVariable String id) {
        var responseUser = userService.getUserById(id);
        return ResponseEntity.ok(responseUser);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<User>> addUser(@RequestBody User user) {
        var responseUserToUpdate = userService.createOrUpdateUser(user);
        return ResponseEntity.ok(responseUserToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<User>> deleteUser(@PathVariable String id) {
        var responseUserToDelete = userService.deleteUser(id);
        return ResponseEntity.ok(responseUserToDelete);
    }

}
