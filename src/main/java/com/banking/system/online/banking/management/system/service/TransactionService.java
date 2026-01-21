package com.banking.system.online.banking.management.system.service;

import com.banking.system.online.banking.management.system.entity.Transaction;
import com.banking.system.online.banking.management.system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByAccountNumber(Long accountNumber) {
        return transactionRepository.findByAccountAccountNumber(accountNumber);
    }
}

