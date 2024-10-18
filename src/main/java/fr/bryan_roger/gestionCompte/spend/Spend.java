package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.Household.Household;
import fr.bryan_roger.gestionCompte.tag.Tag;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Spend implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String name;
    private String date;
    @ManyToOne
    private User payer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaction_recipient",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> recipients;
    private BigDecimal amount;
    @ManyToOne
    private Tag tag;
    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

    public Household getHome() {
        return household;
    }

    public void setHome(Household household) {
        this.household = household;
    }

    public Spend(UUID id, String name, String date, User payer, List<User> recipients, BigDecimal amount, Tag tag, Household household) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.payer = payer;
        this.recipients = recipients;
        this.amount = amount;
        this.tag = tag;
        this.household = household;
    }

    public Spend(String name, String date, User payer, List<User> recipients, BigDecimal amount, Tag tag, Household household) {
        this.name = name;
        this.date = date;
        this.payer = payer;
        this.recipients = recipients;
        this.amount = amount;
        this.tag = tag;
        this.household = household;
    }

    public Spend() {
    }



    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Spend{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", date='" + date + '\'' +
                ", payer=" + payer +
                ", recipients=" + recipients +
                ", amount=" + amount +
                ", tag=" + tag +
                '}';
    }
}
