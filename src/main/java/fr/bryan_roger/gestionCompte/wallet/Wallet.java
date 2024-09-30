package fr.bryan_roger.gestionCompte.wallet;

import fr.bryan_roger.gestionCompte.budget.Budget;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wallet_budget",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "budget_id"))
    @Nullable
    private List<Budget> budgets;
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    public Wallet(UUID id, List<Budget> budgets, Date startDate, Date endDate, boolean isActive) {
        this.id = id;
        this.budgets = budgets;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Wallet( List<Budget> budgets, Date startDate, Date endDate, boolean isActive) {
        this.budgets = budgets;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Wallet() {
    }

    public UUID getId() {
        return id;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
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

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", budgets=" + budgets +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
