package fr.bryan_roger.gestionCompte.wallet;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public ResponseAPI<List<Wallet>> getAllWallets() {
        var wallets = walletRepository.findAll();
        return ResponseApiService.createInstance("200", "Tous les portefeuilles ont été chargé avec succès", wallets);
    }

    public ResponseAPI<Wallet> getWalletById(String id) {
        try {
            var idWallet = UUID.fromString(id);
            var walletFound = walletRepository.getReferenceById(idWallet);
            return ResponseApiService.createInstance("200", "Le portefeuille chargé avec succès", walletFound);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun portefeuille trouvé à l'ID transmis : " + id, null);
        }
    }

    public ResponseAPI<Wallet> createOrUpdateWallet(Wallet wallet) {
        // Si id = 0 on create
        if (wallet.getId() == null || wallet.getId().toString().isEmpty()) {
            var walletToCreate = walletRepository.save(wallet);
            return ResponseApiService.createInstance("201", "Portefeuille créé", walletToCreate);
        }
        // sinon on modifie
        try {
            Wallet walletToUpdate = walletRepository.findById(wallet.getId()).orElseThrow();
            walletToUpdate.setActive(wallet.isActive());
            walletToUpdate.setBudgets(wallet.getBudgets());
            walletToUpdate.setEndDate(wallet.getEndDate());
            walletToUpdate.setStartDate(wallet.getStartDate());


            return ResponseApiService.createInstance("202", "Le portefeuille à été mis à jour", wallet);

        } catch (Exception e) {
            return ResponseApiService.createInstance("402", "Aucun revenu trouvé à l'ID transmis : " + wallet.getId(), null);
        }
    }

    public ResponseAPI<Wallet> deleteWallet(String id) {
        try {
            // s'assurer que l'id est un long
            var idWallet = UUID.fromString(id);
            // vérifier si un revenu avec l'id demandé existe
            walletRepository.deleteById(idWallet);
            return ResponseApiService.createInstance("204", "Le portefeuille a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun portefeuille trouvé à l'ID transmis : " + id, null);
        }
    }
}
