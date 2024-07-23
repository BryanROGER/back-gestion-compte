package fr.bryan_roger.gestionCompte.income;

import fr.bryan_roger.gestionCompte.tag.Tag;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Income implements Serializable {
    private static final  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    @ManyToOne
    private Tag tag;
    @ManyToOne
    private User user;
    private BigDecimal amount;
    private String date; // TODO: combinaison de l'année et du mois en cours

    public Income(long id, Tag tag, User user, BigDecimal amount, String date) {
        this.id = id;
        this.tag = tag;
        this.user = user;
        this.amount = amount;
        this.date = date;
    }

    public Income() {
    }

    public long getId() {
        return id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
