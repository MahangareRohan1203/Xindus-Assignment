package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    public Optional<Products> findByName(String name);
}
