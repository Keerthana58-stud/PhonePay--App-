package com.wallet.walletapp.dto;

public class AuthResponse {

    private String userId;
    private String upiId;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String userId, String upiId, String message) {
        this.userId = userId;
        this.upiId = upiId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getUpiId() {
        return upiId;
    }

    public String getMessage() {
        return message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}