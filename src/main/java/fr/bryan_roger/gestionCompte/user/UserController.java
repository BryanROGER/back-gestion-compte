package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.config.AuthRequestDTO;
import fr.bryan_roger.gestionCompte.config.JwtResponseDTO;
import fr.bryan_roger.gestionCompte.config.JwtService;
import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.List;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<User>>> users() {
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

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        System.out.println(authRequestDTO);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getEmail()))
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }


}
