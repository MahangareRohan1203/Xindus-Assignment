package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

    public Optional<Customers> findByEmail(String email);
}
