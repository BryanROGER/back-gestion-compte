package fr.bryan_roger.gestionCompte.config.auth;

import fr.bryan_roger.gestionCompte.config.AuthRequestDTO;
import fr.bryan_roger.gestionCompte.config.JwtResponseDTO;
import fr.bryan_roger.gestionCompte.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
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
