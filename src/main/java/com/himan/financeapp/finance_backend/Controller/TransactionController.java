package com.himan.financeapp.finance_backend.Controller;

import com.himan.financeapp.finance_backend.DTOs.TransactionRequest;
import com.himan.financeapp.finance_backend.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
//@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionRequest request, Authentication auth) {
        String email = auth.getName(); // Authenticated user's email
        //TransactionService.addTransaction(email, request);
        transactionService.addTransaction(email, request);
        return ResponseEntity.ok("Transaction added successfully");
    }

}
