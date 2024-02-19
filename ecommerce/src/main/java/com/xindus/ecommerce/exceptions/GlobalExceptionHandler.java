package com.xindus.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> authenticationHandler(BadCredentialsException badCredentialsException){
        return new ResponseEntity<>(new ErrorDetails("Log In first", LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> authenticationHandler(AccessDeniedException accessDeniedException){
        return new ResponseEntity<>(new ErrorDetails("You Doesn't have authority to do this request", LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetails> authenticationHandler(AuthenticationException authenticationException){
        return new ResponseEntity<>(new ErrorDetails("Log In first", LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerFound(NoHandlerFoundException noHandlerFoundException){
        return new ResponseEntity<>(new ErrorDetails(noHandlerFoundException.getLocalizedMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception exception){
        return new ResponseEntity<>(new ErrorDetails(exception.getLocalizedMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
