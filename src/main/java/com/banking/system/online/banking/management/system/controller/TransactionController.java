package com.banking.system.online.banking.management.system.controller;

import com.banking.system.online.banking.management.system.entity.Transaction;
import com.banking.system.online.banking.management.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactions(@PathVariable Long accountNumber) {
        return transactionService.getTransactionsByAccountNumber(accountNumber);
    }
}
