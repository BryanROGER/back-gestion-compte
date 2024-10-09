package fr.bryan_roger.gestionCompte.home;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeRepository extends JpaRepository<Home, UUID> {
}
