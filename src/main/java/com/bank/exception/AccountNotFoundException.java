package com.bank.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class AccountNotFoundException  extends RuntimeException{
    public AccountNotFoundException(Long id){
        super("Account " + id + "not found");
    }


}
