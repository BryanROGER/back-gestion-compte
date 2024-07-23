package fr.bryan_roger.gestionCompte.tag;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String label;

    public Tag(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag() {
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
