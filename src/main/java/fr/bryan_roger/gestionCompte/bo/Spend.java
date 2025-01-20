package fr.bryan_roger.gestionCompte.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
    @Column(name = "order_number")
    private long order;

}
