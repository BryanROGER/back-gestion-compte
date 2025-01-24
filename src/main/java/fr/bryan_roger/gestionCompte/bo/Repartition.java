package fr.bryan_roger.gestionCompte.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Repartition implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    private String startDate;
    private String endDate;
    private boolean active;
    @ManyToOne
    private Household household;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
    private BigDecimal partUser1;
    private BigDecimal partUser2;


}