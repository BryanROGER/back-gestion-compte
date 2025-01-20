package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
