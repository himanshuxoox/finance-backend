package com.himan.financeapp.finance_backend.Repository;

import com.himan.financeapp.finance_backend.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'INCOME'")
    Double getTotalIncome(Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'EXPENSE'")
    Double getTotalExpense(Long userId);

    List<Transaction> findTop5ByUserIdOrderByDateDesc(Long userId);
}
