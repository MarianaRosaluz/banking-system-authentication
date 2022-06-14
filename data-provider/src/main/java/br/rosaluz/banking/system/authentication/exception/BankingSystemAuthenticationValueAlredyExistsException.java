package br.rosaluz.banking.system.authentication.exception;

import org.springframework.http.HttpStatus;

public class BankingSystemAuthenticationValueAlredyExistsException extends BankingSystemAuthenticationException {

    public BankingSystemAuthenticationValueAlredyExistsException(String message, String field){
        super(message, field, HttpStatus.BAD_REQUEST);
    }
}
