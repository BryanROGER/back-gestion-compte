package fr.bryan_roger.gestionCompte.bo;

import jakarta.persistence.*;
import lombok.*;

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
public class Household implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> incomeTags;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> spendTags;
    private String name;

}
