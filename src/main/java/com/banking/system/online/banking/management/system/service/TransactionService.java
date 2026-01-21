package com.banking.system.online.banking.management.system.service;

import com.banking.system.online.banking.management.system.dto.TransactionDTO;
import com.banking.system.online.banking.management.system.entity.Transaction;
import com.banking.system.online.banking.management.system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDTO> getTransactionsByAccountNumber(Long accountNumber) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        List<Transaction> transactions = transactionRepository.findByAccountInvolved(accountNumber);

        return transactions.stream().map(tx -> {
            TransactionDTO dto = new TransactionDTO();

            dto.setType(tx.getTransactionType());
            dto.setAmount(tx.getAmount());

            if (tx.getFromAccount() != null)
                dto.setFromAccount(tx.getFromAccount());

            if (tx.getToAccount() != null)
                dto.setToAccount(tx.getToAccount());

            dto.setDate(tx.getTimestamp().format(dateFormatter));
            dto.setTime(tx.getTimestamp().format(timeFormatter));

            return dto;
        }).toList();
    }

}




