package com.xindus.ecommerce.serviceimplementation;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishList;
import com.xindus.ecommerce.entities.WishListItem;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.repositories.CustomerRepository;
import com.xindus.ecommerce.repositories.ProductRepository;
import com.xindus.ecommerce.repositories.WishListItemRepository;
import com.xindus.ecommerce.repositories.WishListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplementationTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private WishListItemRepository wishListItemRepository;

    @InjectMocks
    private CustomerServiceImplementation customerServiceImplementation;

    @Test
    void getAllProductsFromWishList() {
        Customers customer = new Customers();
        WishList wishList = new WishList();
        customer.setWishList(wishList);

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));

        List<WishListItem> list = customerServiceImplementation.getAllProductsFromWishList("rohan@gmail.com");
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void addNewItemToWishList() throws ProductException {
        Customers customer = new Customers();
        Products product = new Products();
        product.setProductId(1);
        WishListItem wishListItem = new WishListItem();
        WishList wishList = new WishList();
        customer.setWishList(wishList);
        wishListItem.setWishList(wishList);
        wishListItem.setProduct(product);

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        when(wishListItemRepository.findByProduct_productIdAndWishList_wishListId(product.getProductId(), customer.getWishList().getWishListId())).thenReturn(Optional.empty());
        when(wishListItemRepository.save(any(WishListItem.class))).thenReturn(wishListItem);

        WishListItem response = customerServiceImplementation.addNewItemToWishList("rohan@gmail.com", product.getProductId());
        assertNotNull(response);
        assertEquals(wishListItem, response);
    }

    @Test
    void deleteWishListItem() throws ProductException {
        Customers customer = new Customers();
        WishListItem wishListItem = new WishListItem();
        wishListItem.setId(1);
        WishList wishList = new WishList();
        customer.setWishList(wishList);
        wishList.setWishListId(1);
        wishListItem.setWishList(wishList);
        wishListItem.getWishList().setCustomer(customer);

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(wishListItemRepository.findById(anyInt())).thenReturn(Optional.of(wishListItem));

        String response = customerServiceImplementation.deleteWishListItem("rohan@gmail.com", 1);
        assertNotNull(response);
        assertEquals("Item deleted from wishlist successfully",response);

    }
}