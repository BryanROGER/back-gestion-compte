package fr.bryan_roger.gestionCompte.wallet;

import fr.bryan_roger.gestionCompte.budget.Budget;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    @ManyToOne(targetEntity = Budget.class)
    private Budget[] budgets;
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    public Wallet(long id, Budget[] budgets, Date startDate, Date endDate, boolean isActive) {
        this.id = id;
        this.budgets = budgets;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Wallet() {
    }

    public long getId() {
        return id;
    }

    public Budget[] getBudgets() {
        return budgets;
    }

    public void setBudgets(Budget[] budgets) {
        this.budgets = budgets;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
