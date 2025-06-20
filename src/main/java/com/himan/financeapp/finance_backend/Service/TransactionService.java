package com.himan.financeapp.finance_backend.Service;

import com.himan.financeapp.finance_backend.DTOs.TransactionRequest;
import com.himan.financeapp.finance_backend.Entity.Transaction;
import com.himan.financeapp.finance_backend.Entity.User;
import com.himan.financeapp.finance_backend.Repository.TransactionRepository;
import com.himan.financeapp.finance_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private UserRepository userRepo;

    public void addTransaction(String email, TransactionRequest request) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Transaction txn = new Transaction();
        txn.setCategory(request.getCategory());
        txn.setAmount(request.getAmount());
        txn.setType(request.getType());
        txn.setDate(LocalDate.parse(request.getDate())); // Convert string to LocalDate
        txn.setUser(user);

        transactionRepo.save(txn);
    }
}
