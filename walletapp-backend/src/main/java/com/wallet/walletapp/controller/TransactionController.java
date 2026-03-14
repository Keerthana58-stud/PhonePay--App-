package com.wallet.walletapp.controller;

import com.wallet.walletapp.model.Transaction;
import com.wallet.walletapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/user/{upiId}")
    public List<Transaction> getTransactionsByUser(@PathVariable String upiId) {
        return transactionService.getTransactionsByUser(upiId);
    }

    @GetMapping("/sender/{senderUpi}")
    public List<Transaction> getBySender(@PathVariable String senderUpi) {
        return transactionService.getTransactionsBySender(senderUpi);
    }

    @GetMapping("/receiver/{receiverUpi}")
    public List<Transaction> getByReceiver(@PathVariable String receiverUpi) {
        return transactionService.getTransactionsByReceiver(receiverUpi);
    }
}