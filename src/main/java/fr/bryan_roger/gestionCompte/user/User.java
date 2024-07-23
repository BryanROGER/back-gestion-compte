package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.wallet.Wallet;
import jakarta.persistence.*;


import java.io.Serializable;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String lastname;
    private String firstname;
    private String color; //TODO: mettre une couleur par default avec des random sur les RGB
    @OneToOne
    private Wallet wallet;


    public long getId() {
        return id;
    }

    public User(String lastname, String firstname, String color) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.color = color;
    }

    public User(long id, String lastname, String firstname, String color) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
                ", color='" + color + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
