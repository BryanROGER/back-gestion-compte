package fr.bryan_roger.gestionCompte.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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
@Table(name = "\"user\"")
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
    @Builder.Default
    private List<Household> households = new ArrayList<>();


}
