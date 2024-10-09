package fr.bryan_roger.gestionCompte.login;

import fr.bryan_roger.gestionCompte.config.JwtService;
import fr.bryan_roger.gestionCompte.dto.AuthentificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtService JwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody AuthentificationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(JwtService.GenerateToken(user.getUsername()));
        }
        return ResponseEntity.status(400).body("Présence de certaines erreurs");
    }

}
