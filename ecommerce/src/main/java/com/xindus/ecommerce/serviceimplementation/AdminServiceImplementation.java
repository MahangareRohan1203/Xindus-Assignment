package com.xindus.ecommerce.serviceimplementation;

import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.repositories.ProductRepository;
import com.xindus.ecommerce.serviceInterfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Products addNewProduct(Products product) throws ProductException {
        if (productRepository.findByName(product.getName()).isPresent())
            throw new ProductException("Product with given name already exists");
        product = productRepository.save(product);
        return product;
    }
}
