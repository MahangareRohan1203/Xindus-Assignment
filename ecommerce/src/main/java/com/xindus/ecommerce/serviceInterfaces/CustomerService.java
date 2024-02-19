package com.xindus.ecommerce.serviceInterfaces;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishListItem;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.exceptions.ProductException;

import java.util.List;

public interface CustomerService {
    public Customers addNewCustomer(Customers customer) throws CustomerException;

    public List<WishListItem> getAllProductsFromWishList(String email) throws  CustomerException;

    public WishListItem addNewItemToWishList(String email, Integer productId) throws CustomerException, ProductException;

    public String deleteWishListItem(String email, Integer id) throws  ProductException;

}
