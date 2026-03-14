package com.wallet.walletapp.service;

import com.wallet.walletapp.model.Transaction;
import com.wallet.walletapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsBySender(String senderUpi) {
        return transactionRepository.findBySenderUpi(senderUpi);
    }

    public List<Transaction> getTransactionsByReceiver(String receiverUpi) {
        return transactionRepository.findByReceiverUpi(receiverUpi);
    }

    public List<Transaction> getTransactionsByUser(String upiId) {

        List<Transaction> result = new ArrayList<>();

        List<Transaction> sent = transactionRepository.findBySenderUpi(upiId);
        List<Transaction> received = transactionRepository.findByReceiverUpi(upiId);

        if (sent != null) result.addAll(sent);
        if (received != null) result.addAll(received);

        return result;
    }
}