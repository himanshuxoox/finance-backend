package com.himan.financeapp.finance_backend.Service;

import com.himan.financeapp.finance_backend.DTOs.DashboardResponse;
import com.himan.financeapp.finance_backend.Entity.Transaction;
import com.himan.financeapp.finance_backend.Entity.User;
import com.himan.financeapp.finance_backend.Repository.TransactionRepository;
import com.himan.financeapp.finance_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private UserRepository userRepo;

    public DashboardResponse getDashboardData(String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Double income = transactionRepo.getTotalIncome(user.getId());
        Double expense = transactionRepo.getTotalExpense(user.getId());
        List<Transaction> transactions = transactionRepo.findTop5ByUserIdOrderByDateDesc(user.getId());

        DashboardResponse response = new DashboardResponse();
        response.setTotalIncome(income != null ? income : 0.0);
        response.setTotalExpense(expense != null ? expense : 0.0);
        response.setRemaining(response.getTotalIncome() - response.getTotalExpense());
        response.setRecentTransactions(transactions);
        return response;
    }
}
