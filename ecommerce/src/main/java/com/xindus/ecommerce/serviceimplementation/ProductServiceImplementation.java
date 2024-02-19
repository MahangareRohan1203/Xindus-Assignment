package com.xindus.ecommerce.serviceimplementation;

import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.repositories.ProductRepository;
import com.xindus.ecommerce.serviceInterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }
}
