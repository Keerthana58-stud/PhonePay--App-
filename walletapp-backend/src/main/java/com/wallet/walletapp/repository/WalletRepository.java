package com.wallet.walletapp.repository;

import com.wallet.walletapp.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {

    // Find wallet by userId
    Optional<Wallet> findByUserId(String userId);

    // Check if wallet already exists for user
    boolean existsByUserId(String userId);
}