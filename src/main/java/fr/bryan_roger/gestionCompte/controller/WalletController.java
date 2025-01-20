package fr.bryan_roger.gestionCompte.controller;

import fr.bryan_roger.gestionCompte.bo.Wallet;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.bll.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/wallets")
@RestController
@CrossOrigin
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<Wallet>>> wallets() {
        var responseWallet = walletService.getAll();
        return ResponseEntity.ok(responseWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Wallet>> getWallet(@PathVariable String id) {
        var responseWallet = walletService.getById(UUID.fromString(id));
        return ResponseEntity.ok(responseWallet);
    }

    @PostMapping("")
    public ResponseEntity<ResponseAPI<Wallet>> addWallet(@RequestBody Wallet wallet) {
        var responseWalletToAdd = walletService.create(wallet);
        return ResponseEntity.ok(responseWalletToAdd);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI<Wallet>> updateWallet(@RequestBody Wallet wallet, @PathVariable String id) {
        var responseWalletToUpdate = walletService.update(UUID.fromString(id),wallet);
        return ResponseEntity.ok(responseWalletToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI<Wallet>> deleteWallet(@PathVariable String id) {
        var responseWalletToDelete = walletService.delete(UUID.fromString(id));
        return ResponseEntity.ok(responseWalletToDelete);
    }

}
