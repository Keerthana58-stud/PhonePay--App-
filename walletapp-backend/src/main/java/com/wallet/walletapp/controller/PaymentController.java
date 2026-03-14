package com.wallet.walletapp.controller;

import com.wallet.walletapp.dto.PaymentRequest;
import com.wallet.walletapp.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/send")
    public String sendMoney(@RequestBody PaymentRequest request) {
        return paymentService.sendMoney(request);
    }
}