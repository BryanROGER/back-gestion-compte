package fr.bryan_roger.gestionCompte.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.bryan_roger.gestionCompte.config.security.UserRole;
import fr.bryan_roger.gestionCompte.Household.Household;
import fr.bryan_roger.gestionCompte.wallet.Wallet;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    private String email;
    private String lastname;
    private String firstname;
    private String backgroundColor;
    private String letterColor;
    @OneToOne
    private Wallet wallet;
    private BigDecimal repartition;
    @JsonIgnore
    private String password;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Household> households = new ArrayList<>();


    public User(String lastname, String firstname, String backgroundColor, String letterColor, Wallet wallet, BigDecimal repartition, String email, String password, Set<UserRole> roles, List<Household> households) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.backgroundColor = backgroundColor;
        this.letterColor = letterColor;
        this.wallet = wallet;
        this.repartition = repartition;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.households = households;
    }

    public User() {
    }

    public List<Household> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<Household> households) {
        this.households = households;
    }

    public BigDecimal getRepartition() {
        return repartition;
    }

    public void setRepartition(BigDecimal repartition) {
        this.repartition = repartition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getLetterColor() {
        return letterColor;
    }

    public void setLetterColor(String letterColor) {
        this.letterColor = letterColor;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", color='" + letterColor + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
