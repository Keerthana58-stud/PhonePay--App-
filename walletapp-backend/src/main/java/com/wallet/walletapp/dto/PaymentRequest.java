package com.wallet.walletapp.dto;

public class PaymentRequest {

    private String senderUpi;
    private String receiverUpi;
    private double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String senderUpi, String receiverUpi, double amount) {
        this.senderUpi = senderUpi;
        this.receiverUpi = receiverUpi;
        this.amount = amount;
    }

    public String getSenderUpi() {
        return senderUpi;
    }

    public void setSenderUpi(String senderUpi) {
        this.senderUpi = senderUpi;
    }

    public String getReceiverUpi() {
        return receiverUpi;
    }

    public void setReceiverUpi(String receiverUpi) {
        this.receiverUpi = receiverUpi;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}