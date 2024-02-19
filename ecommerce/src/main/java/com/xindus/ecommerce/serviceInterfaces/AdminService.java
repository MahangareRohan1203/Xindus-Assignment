package com.xindus.ecommerce.serviceInterfaces;

import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.exceptions.ProductException;

public interface AdminService {
    public Products addNewProduct(Products product) throws ProductException;
}
