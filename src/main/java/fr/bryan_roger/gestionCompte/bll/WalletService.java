package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.bo.Wallet;
import fr.bryan_roger.gestionCompte.dal.WalletRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService extends CrudGenericService<Wallet, UUID>{

    public WalletService(WalletRepository walletRepository) {

        super(walletRepository,"portefeuille");
    }
}
