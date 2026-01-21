package com.banking.system.online.banking.management.system.controller;

import com.banking.system.online.banking.management.system.dto.DepositRequest;
import com.banking.system.online.banking.management.system.dto.TransferRequest;
import com.banking.system.online.banking.management.system.entity.Account;
import com.banking.system.online.banking.management.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create/{userId}")
    public Account createAccount(@PathVariable Long userId) {

        return accountService.createAccount(userId);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestBody DepositRequest dto) {
        accountService.deposit(dto);
        return "Money added successfully";

    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest dto){
        accountService.transfer(dto);
        return "Transfer Succesful";
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/user/{userId}")
    public Account getAccountByUser(@PathVariable Long userId) {
        return accountService.getAccountByUserId(userId);
    }
}
