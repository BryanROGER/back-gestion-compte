package fr.bryan_roger.gestionCompte.user;


import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<User>>> users() {
        var responseUsers = userService.getAllUsers();
        return ResponseEntity.ok(responseUsers);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseAPI<User>> getUser(@PathVariable String email) {
        var responseUser = userService.getUserById(email);
        return ResponseEntity.ok(responseUser);
    }
//
//    @PutMapping("/add")
//    public ResponseEntity<ResponseAPI<User>> addUser(@RequestBody User user) {
//        var responseUserToUpdate = userService.createOrUpdateUser(user);
//        return ResponseEntity.ok(responseUserToUpdate);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<User>> deleteUser(@PathVariable String id) {
        var responseUserToDelete = userService.deleteUser(id);
        return ResponseEntity.ok(responseUserToDelete);
    }



}
