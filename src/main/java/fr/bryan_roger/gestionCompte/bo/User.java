package fr.bryan_roger.gestionCompte.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    private String email;
    private String lastname;
    private String firstname;
    private String backgroundColor;
    private String letterColor;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
      private String role;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Household> households = new ArrayList<>();


}
