package com.bank.controller;


import com.bank.model.Account;
import com.bank.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    public record CreateAccountRequest(@NotBlank  String firstName, @NotBlank  String lastName, @NotNull @PositiveOrZero BigDecimal openingBalance) {
    }

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody  CreateAccountRequest request){
        Account created = accountService.create(request.firstName(), request.lastName(), request.openingBalance());
        return ResponseEntity.created(URI.create("/accounts/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id){
        return accountService.get(id);
    }


}
