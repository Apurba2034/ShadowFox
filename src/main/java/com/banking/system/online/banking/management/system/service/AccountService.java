package com.banking.system.online.banking.management.system.service;

import com.banking.system.online.banking.management.system.dto.DepositRequest;
import com.banking.system.online.banking.management.system.dto.TransferRequest;
import com.banking.system.online.banking.management.system.entity.Account;
import com.banking.system.online.banking.management.system.entity.Transaction;
import com.banking.system.online.banking.management.system.entity.User;
import com.banking.system.online.banking.management.system.repository.AccountRepository;
import com.banking.system.online.banking.management.system.repository.TransactionRepository;
import com.banking.system.online.banking.management.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final long START_ACCOUNT_NUMBER = 10000100L;

    private long generateAccountNumber() {
        Long last = accountRepository.findMaxAccountNumber();
        return last == null ? START_ACCOUNT_NUMBER : last + 1;
    }

    public Account createAccount(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);
        account.setAccountNumber(generateAccountNumber());
        return accountRepository.save(account);
    }
    public void deposit(DepositRequest dto) {
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + dto.getAmount());
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setToAccount(account);
        tx.setAmount(dto.getAmount());
        tx.setTransaction_type("CREDIT");
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
    }

    public void transfer(TransferRequest dto) {

        Account from = accountRepository.findByAccountNumber(dto.getFromAccountNumber())
                .orElseThrow(() -> new RuntimeException("From account not found"));

        Account to = accountRepository.findByAccountNumber(dto.getToAccountNumber())
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (from.getBalance() < dto.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance() - dto.getAmount());
        to.setBalance(to.getBalance() + dto.getAmount());

        accountRepository.save(from);
        accountRepository.save(to);


        Transaction debit = new Transaction();
        debit.setFromAccount(from);
        debit.setToAccount(to);
        debit.setAmount(dto.getAmount());
        debit.setTransaction_type("DEBIT");
        debit.setTimestamp(LocalDateTime.now());

        Transaction credit = new Transaction();
        credit.setFromAccount(from);
        credit.setToAccount(to);
        credit.setAmount(dto.getAmount());
        credit.setTransaction_type("CREDIT");
        credit.setTimestamp(LocalDateTime.now());


        transactionRepository.save(debit);
        transactionRepository.save(credit);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }


    public Account getAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}




