package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accounts;
    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Account create(String firstName, String lastName, BigDecimal openingBalance) {
        return accounts.save(new Account(firstName,lastName,openingBalance));
    }
    public Account get(Long id) {
        return accounts.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }
}
