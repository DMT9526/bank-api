package com.bank.controller;


import com.bank.model.Account;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public record CreateAccountRequest(String firstName, String lastName, BigDecimal openingBalance) {
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody CreateAccountRequest request){
        Account created = accountService.create(request.firstName(), request.lastName(), request.openingBalance());
        return ResponseEntity.created(URI.create("/accounts/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id){
        return accountService.get(id);
    }


}
