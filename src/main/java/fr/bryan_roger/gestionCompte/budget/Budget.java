package fr.bryan_roger.gestionCompte.budget;

import fr.bryan_roger.gestionCompte.tag.Tag;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private double amount;
    @OneToOne
    private Tag tag;

    public Budget() {
    }

    public Budget(long id, double amount, Tag tag) {
        this.id = id;
        this.amount = amount;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
