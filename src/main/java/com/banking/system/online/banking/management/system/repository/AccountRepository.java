package com.banking.system.online.banking.management.system.repository;

import com.banking.system.online.banking.management.system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT MAX(a.accountNumber) FROM Account a")
    Long findMaxAccountNumber();
    Optional<Account> findByAccountNumber(Long accountNumber);
    Optional<Account> findByUserId(Long userId);


}