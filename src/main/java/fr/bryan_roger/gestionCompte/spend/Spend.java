package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.tag.Tag;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Spend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String nom;
    private String date; // TODO : combinaison de l'année + du mois
    @ManyToOne
    private User payer;
    @ManyToMany
    @JoinTable(
            name = "transaction_recipient",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> recipients;
    private BigDecimal amount;
    @ManyToOne
    private Tag tag;

    public Spend(long id, String nom, String date, User payer, List<User> recipients, BigDecimal amount, Tag tag) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.payer = payer;
        this.recipients = recipients;
        this.amount = amount;
        this.tag = tag;
    }

    public Spend() {
    }



    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public void setId(long id) {
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


}
