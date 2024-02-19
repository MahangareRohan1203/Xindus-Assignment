package com.xindus.ecommerce.controllers;

import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.serviceInterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProductListHandler() {
        List<Products> list = productService.getAllProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
