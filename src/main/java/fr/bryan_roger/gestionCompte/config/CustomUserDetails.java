package fr.bryan_roger.gestionCompte.config;

import fr.bryan_roger.gestionCompte.config.security.UserRole;
import fr.bryan_roger.gestionCompte.user.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {

    private final String email;
    private final String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User userByEmail) {
        this.email = userByEmail.getEmail();
        this.password = userByEmail.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(UserRole role : userByEmail.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}