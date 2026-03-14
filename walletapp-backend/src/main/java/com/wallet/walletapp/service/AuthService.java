package com.wallet.walletapp.service;

import com.wallet.walletapp.dto.AuthResponse;
import com.wallet.walletapp.dto.LoginRequest;
import com.wallet.walletapp.dto.SignupRequest;
import com.wallet.walletapp.model.User;
import com.wallet.walletapp.model.Wallet;
import com.wallet.walletapp.repository.UserRepository;
import com.wallet.walletapp.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public AuthService(UserRepository userRepository,
                       WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public AuthResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        if (userRepository.existsByUpiId(request.getUpiId())) {
            throw new RuntimeException("UPI ID already exists");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword(),
                request.getUpiId()
        );

        User savedUser = userRepository.save(user);

        if (!walletRepository.existsByUserId(savedUser.getId())) {
            Wallet wallet = new Wallet(savedUser.getId(), 0.0);
            walletRepository.save(wallet);
        }

        return new AuthResponse(savedUser.getId(), savedUser.getUpiId(), "Signup successful");
    }

    public AuthResponse login(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new AuthResponse(user.getId(), user.getUpiId(), "Login successful");
    }
}