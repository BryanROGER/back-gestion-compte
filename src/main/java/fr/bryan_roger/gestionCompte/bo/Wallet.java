package fr.bryan_roger.gestionCompte.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wallet_budget",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "budget_id"))
    @Nullable
    private List<Budget> budgets;
    private String startDate;
    private String endDate;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.EAGER)
    private Household household;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
