package fr.bryan_roger.gestionCompte.wallet;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wallet")
@RestController
@CrossOrigin
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Wallet>>> wallets(Model model) {
        var responseWallet = walletService.getAllWallets();
        return ResponseEntity.ok(responseWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Wallet>> getWallet(@PathVariable String id) {
        var responseWallet = walletService.getWalletById(id);
        return ResponseEntity.ok(responseWallet);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<Wallet>> addWallet(@RequestBody Wallet wallet) {
        var responseWalletToUpdate = walletService.createOrUpdateWallet(wallet);
        return ResponseEntity.ok(responseWalletToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Wallet>> deleteWallet(@PathVariable String id) {
        var responseWalletToDelete = walletService.deleteWallet(id);
        return ResponseEntity.ok(responseWalletToDelete);
    }

}
