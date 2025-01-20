package fr.bryan_roger.gestionCompte.dal;

import fr.bryan_roger.gestionCompte.bo.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
