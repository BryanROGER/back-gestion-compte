package fr.bryan_roger.gestionCompte.tag;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    private String label;
    private boolean income = false;
    private boolean spend = false;

    public Tag(UUID id, String label, boolean income, boolean spend) {
        this.id = id;
        this.label = label;
        this.income = income;
        this.spend = spend;
    }

    public Tag(String label, boolean income, boolean spend) {
        this.label = label;
        this.income = income;
        this.spend = spend;
    }

    public Tag() {
    }

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
