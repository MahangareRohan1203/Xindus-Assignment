package com.xindus.ecommerce.controllers;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.responses.LogInResponse;
import com.xindus.ecommerce.serviceInterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<Customers> addNewCustomerHandler(@RequestBody @Validated Customers customer) throws CustomerException {
        customer.setRole("USER");
        customer = customerService.addNewCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<LogInResponse> logInRequestHandler(Authentication authentication) {
        return new ResponseEntity<>(new LogInResponse("Log In Successful", "Token is added in header with key as Authorization", LocalDateTime.now()), HttpStatus.ACCEPTED);
    }


}
