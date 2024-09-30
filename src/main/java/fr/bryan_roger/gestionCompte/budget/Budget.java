package fr.bryan_roger.gestionCompte.budget;

import fr.bryan_roger.gestionCompte.tag.Tag;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Budget implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    private BigDecimal amount;
    @ManyToOne
    private Tag tag;

    public Budget() {
    }

    public Budget(UUID id, BigDecimal amount, Tag tag) {
        this.id = id;
        this.amount = amount;
        this.tag = tag;
    }

    public Budget(BigDecimal amount, Tag tag) {
        this.amount = amount;
        this.tag = tag;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", amount=" + amount +
                ", tag=" + tag +
                '}';
    }
}
