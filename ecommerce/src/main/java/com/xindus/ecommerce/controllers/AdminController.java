package com.xindus.ecommerce.controllers;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.responses.LogInResponse;
import com.xindus.ecommerce.serviceInterfaces.AdminService;
import com.xindus.ecommerce.serviceInterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/signIn")
    public ResponseEntity<LogInResponse> adminLogIn(Authentication authentication) {
        return new ResponseEntity<>(new LogInResponse("Log In Successful", "Token is added in Header", LocalDateTime.now()), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Products> addNewProductHandler(@RequestBody @Validated Products product) throws ProductException {
        adminService.addNewProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<Customers> addNewCustomerHandler(@RequestBody @Validated Customers customer) throws CustomerException {
        customer.setRole("ADMIN");
        customer = customerService.addNewCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
