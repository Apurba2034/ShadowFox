package com.banking.system.online.banking.management.system.repository;

import com.banking.system.online.banking.management.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountAccountNumber(Long accountNumber);

}