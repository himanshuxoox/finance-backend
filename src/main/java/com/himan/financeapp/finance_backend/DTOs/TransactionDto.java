package com.himan.financeapp.finance_backend.DTOs;

import com.himan.financeapp.finance_backend.Entity.Transaction;
import lombok.Data;


@Data
public class TransactionDto {
    private String category;
    private double amount;
    private String type;
    private String date;

    public TransactionDto(Transaction txn) {
        this.category = txn.getCategory();
        this.amount = txn.getAmount();
        this.type = txn.getType();
        this.date = txn.getDate().toString(); // Convert LocalDate to String
    }

}
