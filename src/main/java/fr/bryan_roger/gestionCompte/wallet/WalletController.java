package fr.bryan_roger.gestionCompte.wallet;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wallet")
@RestController
@CrossOrigin
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Wallet>>> wallets() {
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
