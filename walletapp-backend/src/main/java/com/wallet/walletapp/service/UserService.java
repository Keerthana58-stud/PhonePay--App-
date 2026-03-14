package com.wallet.walletapp.service;

import com.wallet.walletapp.model.User;
import com.wallet.walletapp.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(@NonNull User user) {

        String email = Objects.requireNonNull(user.getEmail(), "Email cannot be null");
        String phone = Objects.requireNonNull(user.getPhone(), "Phone cannot be null");

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(phone)) {
            throw new RuntimeException("Phone already exists");
        }

        if (user.getUpiId() != null && !user.getUpiId().isBlank()
                && userRepository.existsByUpiId(user.getUpiId())) {
            throw new RuntimeException("UPI ID already exists");
        }

        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by internal Mongo id
    public Optional<User> getUserById(@NonNull String id) {
        return userRepository.findById(id);
    }

    // Get user by email
    public Optional<User> getUserByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    // Get user by UPI ID
    public Optional<User> getUserByUpiId(@NonNull String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    // Update user
    public User updateUser(@NonNull String id, @NonNull User updatedUser) {

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setName(updatedUser.getName());
        existing.setPhone(updatedUser.getPhone());

        if (updatedUser.getEmail() != null &&
                !updatedUser.getEmail().equals(existing.getEmail())) {

            if (userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            existing.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getUpiId() != null &&
                !updatedUser.getUpiId().equals(existing.getUpiId())) {

            if (userRepository.existsByUpiId(updatedUser.getUpiId())) {
                throw new RuntimeException("UPI ID already exists");
            }

            existing.setUpiId(updatedUser.getUpiId());
        }

        return userRepository.save(existing);
    }

    // Delete user
    public void deleteUser(@NonNull String id) {
        userRepository.deleteById(id);
    }
}