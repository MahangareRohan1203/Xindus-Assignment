package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.Customers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByEmail() {
        String email = "test@gmail.com";
        Customers customers = new Customers();
        customers.setEmail(email);
        Optional<Customers> isExist = customerRepository.findByEmail(email);
        assertEquals(false, isExist.isPresent());


        customerRepository.save(customers);
        isExist = customerRepository.findByEmail(email);
        assertEquals(true, isExist.isPresent());
    }
}