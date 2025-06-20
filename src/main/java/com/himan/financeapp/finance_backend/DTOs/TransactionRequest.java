package com.himan.financeapp.finance_backend.DTOs;

import lombok.Data;

@Data
public class TransactionRequest {

    private String category;
    private double amount;
    private String type; // "INCOME" or "EXPENSE"
    private String date; // yyyy-MM-dd
}
