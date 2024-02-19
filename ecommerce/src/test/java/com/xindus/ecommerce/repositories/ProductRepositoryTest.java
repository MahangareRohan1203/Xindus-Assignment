package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByName() {
        String name = "product-1";
        Products product = new Products();
        product.setName(name);
        assertFalse(productRepository.findByName(name).isPresent());

        productRepository.save(product);
        assertTrue(productRepository.findByName(name).isPresent());

    }
}