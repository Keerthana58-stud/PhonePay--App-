package com.wallet.walletapp.service;

import com.wallet.walletapp.dto.PaymentRequest;
import com.wallet.walletapp.model.Transaction;
import com.wallet.walletapp.model.User;
import com.wallet.walletapp.model.Wallet;
import com.wallet.walletapp.repository.TransactionRepository;
import com.wallet.walletapp.repository.UserRepository;
import com.wallet.walletapp.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public PaymentService(WalletRepository walletRepository,
                          TransactionRepository transactionRepository,
                          UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String sendMoney(PaymentRequest request) {

        User senderUser = userRepository.findByUpiId(request.getSenderUpi())
                .orElseThrow(() -> new RuntimeException("Sender UPI not found"));

        User receiverUser = userRepository.findByUpiId(request.getReceiverUpi())
                .orElseThrow(() -> new RuntimeException("Receiver UPI not found"));

        Wallet senderWallet = walletRepository.findByUserId(senderUser.getId())
                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findByUserId(receiverUser.getId())
                .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

        if (senderWallet.getBalance() < request.getAmount()) {
            Transaction failedTx = new Transaction(
                    request.getSenderUpi(),
                    request.getReceiverUpi(),
                    request.getAmount(),
                    "FAILED"
            );

            transactionRepository.save(failedTx);
            throw new RuntimeException("Insufficient balance");
        }

        senderWallet.setBalance(senderWallet.getBalance() - request.getAmount());
        receiverWallet.setBalance(receiverWallet.getBalance() + request.getAmount());

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        Transaction transaction = new Transaction(
                request.getSenderUpi(),
                request.getReceiverUpi(),
                request.getAmount(),
                "SUCCESS"
        );

        transactionRepository.save(transaction);

        return "Payment successful";
    }
}