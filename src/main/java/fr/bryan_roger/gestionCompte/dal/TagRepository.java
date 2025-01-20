package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
