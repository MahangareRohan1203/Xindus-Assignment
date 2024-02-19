package com.xindus.ecommerce.serviceimplementation;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishList;
import com.xindus.ecommerce.entities.WishListItem;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.repositories.CustomerRepository;
import com.xindus.ecommerce.repositories.ProductRepository;
import com.xindus.ecommerce.repositories.WishListItemRepository;
import com.xindus.ecommerce.repositories.WishListRepository;
import com.xindus.ecommerce.serviceInterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishListItemRepository wishListItemRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public Customers addNewCustomer(Customers customer) throws CustomerException {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent())
            throw new CustomerException("Customer with given mail already exists ");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        WishList wishList = new WishList();
        wishList.setCustomer(customer);
        wishListRepository.save(wishList);
        return customer;
    }

    @Override
    public List<WishListItem> getAllProductsFromWishList(String email) throws CustomerException {
        Customers customer = customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found"));
        return customer.getWishList().getWishListItems();
    }

    @Override
    public WishListItem addNewItemToWishList(String email, Integer productId) throws CustomerException, ProductException {
        Customers customer = customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found"));
        Products product = productRepository.findById(productId).orElseThrow(() -> new ProductException("Product Not found with given id"));

        if (wishListItemRepository.findByProduct_productIdAndWishList_wishListId(productId, customer.getWishList().getWishListId()).isPresent()) {
            throw new ProductException("Product Already exists in wishlist");
        }

        WishListItem wishListItem = new WishListItem();
        wishListItem.setWishList(customer.getWishList());
        wishListItem.setProduct(product);
        wishListItem = wishListItemRepository.save(wishListItem);
        return wishListItem;
    }

    @Override
    public String deleteWishListItem(String email, Integer id) throws ProductException {
        Customers customer = customerRepository.findByEmail(email).orElseThrow(() -> new ProductException("Customer not found"));
        WishListItem wishListItem = wishListItemRepository.findById(id).orElseThrow(() -> new CustomerException("Item Not found with given id"));
        if (!Objects.equals(wishListItem.getWishList().getWishListId(), customer.getWishList().getWishListId()))
            throw new ProductException("You are trying to remove another' s product");
        wishListItemRepository.deleteById(id);
        return "Item deleted from wishlist successfully";
    }

}
