package com.himan.financeapp.finance_backend.DTOs;

import com.himan.financeapp.finance_backend.Entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Double totalIncome;
    private Double totalExpense;
    private Double remaining;
    private List<TransactionDto> recentTransactions;
    private String name;
    // Add chart data if needed
    //private List<ChartDto> chartDtoList;
}
