package com.wallet.walletapp.service;

import com.wallet.walletapp.model.Wallet;
import com.wallet.walletapp.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // ✅ Create wallet for user
    public Wallet createWallet(String userId) {

        if (walletRepository.existsByUserId(userId)) {
            throw new RuntimeException("Wallet already exists");
        }

        Wallet wallet = new Wallet(userId, 0.0);

        return walletRepository.save(wallet);
    }

    // ✅ Add money
    @Transactional
    public Wallet addMoney(String userId, double amount) {

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        wallet.setBalance(wallet.getBalance() + amount);

        return walletRepository.save(wallet);
    }

    // ✅ Get wallet
    public Wallet getWallet(String userId) {

        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}