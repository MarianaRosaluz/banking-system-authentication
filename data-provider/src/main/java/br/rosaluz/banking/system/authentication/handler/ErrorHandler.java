package br.rosaluz.banking.system.authentication.handler;


import br.rosaluz.banking.system.authentication.exception.BankingSystemAuthenticationException;
import br.rosaluz.banking.system.authentication.handler.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({BankingSystemAuthenticationException.class})
    public ResponseEntity<?> handleBankingSystemAuthenticationException(final BankingSystemAuthenticationException bankingSystemAuthenticationException) {
        return ResponseEntity.status(bankingSystemAuthenticationException.getStatus()).body(
                ErrorResponse.builder()
                        .codeStatus(bankingSystemAuthenticationException.getStatus().value())
                        .field(bankingSystemAuthenticationException.getFieldError())
                        .description(bankingSystemAuthenticationException.getMessage())
                        .build()
        );
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleBadCredentialsException(final BadCredentialsException badCredentialsException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .codeStatus(HttpStatus.NOT_FOUND.value())
                        .field(badCredentialsException.getCause().getMessage())
                        .description(badCredentialsException.getMessage())
                        .build()
        );
    }
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException(final AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .codeStatus(HttpStatus.NOT_FOUND.value())
                        .field(authenticationException.getCause().getMessage())
                        .description(authenticationException.getMessage())
                        .build()
        );
    }

}
