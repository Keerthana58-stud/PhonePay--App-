package com.wallet.walletapp.controller;

import com.wallet.walletapp.model.Wallet;
import com.wallet.walletapp.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // ✅ Create wallet
    @PostMapping("/{userId}")
    public Wallet createWallet(@PathVariable String userId) {
        return walletService.createWallet(userId);
    }

    // ✅ Add money
    @PostMapping("/{userId}/add")
    public Wallet addMoney(@PathVariable String userId,
                           @RequestParam double amount) {
        return walletService.addMoney(userId, amount);
    }

    // ✅ Get wallet
    @GetMapping("/{userId}")
    public Wallet getWallet(@PathVariable String userId) {
        return walletService.getWallet(userId);
    }
}