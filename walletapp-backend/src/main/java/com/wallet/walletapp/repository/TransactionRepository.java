package com.wallet.walletapp.repository;

import com.wallet.walletapp.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findBySenderUpi(String senderUpi);

    List<Transaction> findByReceiverUpi(String receiverUpi);
}