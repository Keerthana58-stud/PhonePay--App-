package com.wallet.walletapp.controller;

import com.wallet.walletapp.model.User;
import com.wallet.walletapp.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping
    public User createUser(@RequestBody @NonNull User user) {
        return userService.createUser(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable @NonNull String id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable @NonNull String id,
                           @RequestBody @NonNull User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable @NonNull String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}