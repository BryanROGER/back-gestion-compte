package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.wallet.Wallet;
import jakarta.persistence.*;


import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String lastname;
    private String firstname;
    private String backgroundColor;
    private String letterColor;
    @OneToOne
    private Wallet wallet;


    public UUID getId() {
        return id;
    }

    public User(UUID id, String lastname, String firstname, String backgroundColor, String letterColor, Wallet wallet) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.backgroundColor = backgroundColor;
        this.letterColor = letterColor;
        this.wallet = wallet;
    }

    public User(String lastname, String firstname, String backgroundColor, String letterColor, Wallet wallet) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.backgroundColor = backgroundColor;
        this.letterColor = letterColor;
        this.wallet = wallet;
    }

    public User() {
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

    public void setId(UUID id) {
        this.id = id;
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
