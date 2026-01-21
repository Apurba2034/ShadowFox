package com.banking.system.online.banking.management.system.repository;

import com.banking.system.online.banking.management.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccountNumberOrToAccountNumber(
            Long fromAccountNumber, Long toAccountNumber
    );
}
