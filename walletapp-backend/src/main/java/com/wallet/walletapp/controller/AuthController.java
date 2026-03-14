package com.wallet.walletapp.controller;

import com.wallet.walletapp.dto.AuthResponse;
import com.wallet.walletapp.dto.LoginRequest;
import com.wallet.walletapp.dto.SignupRequest;
import com.wallet.walletapp.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}