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
public class Income implements Serializable {
    @Serial
    private static final  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    @ManyToOne
    private Tag tag;
    @ManyToOne
    private User user;
    private BigDecimal amount;
    private String date;
    @Column(name = "order_number")
    private long order;
}
