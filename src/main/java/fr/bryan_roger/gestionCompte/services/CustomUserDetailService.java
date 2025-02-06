package fr.bryan_roger.gestionCompte.services;

import fr.bryan_roger.gestionCompte.dal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        log.info("User '{}' authenticated with role: {}", user.getEmail(), authority.getAuthority());
        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
