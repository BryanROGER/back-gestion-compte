package fr.bryan_roger.gestionCompte.config.security;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Table(name = "ROLES")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public UserRole(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRole() {
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}