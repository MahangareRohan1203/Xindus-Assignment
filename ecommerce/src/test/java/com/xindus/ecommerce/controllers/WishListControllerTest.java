package com.xindus.ecommerce.controllers;

import com.xindus.ecommerce.configuration.JwtMethods;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishListItem;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.responses.EcommerceResponse;
import com.xindus.ecommerce.serviceInterfaces.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishListControllerTest {

    @Mock
    private JwtMethods jwtMethods;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private WishListController wishListController;


    @Test
    void wishListItems() {
        String jwt = "Bearer tokenkdsjkslajkasjdksd.kafsddssdfjk.jksfdsdjklf";
        String email = "rohan@gmail.com";
        when(jwtMethods.getEmailFromToken(jwt)).thenReturn(email);

        List<WishListItem> list = new ArrayList<>();
        when(customerService.getAllProductsFromWishList(email)).thenReturn(list);

        ResponseEntity<List<WishListItem>> response = wishListController.wishListItems(jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    void addItemToWishList() throws ProductException {
        String jwt = "Bearer tokenkdsjkslajkasjdksd.kafsddssdfjk.jksfdsdjklf";
        String email = "rohan@gmail.com";

        Products product = new Products();
        product.setProductId(1);
        when(jwtMethods.getEmailFromToken(jwt)).thenReturn(email);

        WishListItem mockItem= new WishListItem();
        when(customerService.addNewItemToWishList(email, product.getProductId())).thenReturn(mockItem);

        ResponseEntity<WishListItem> response = wishListController.addItemToWishList(jwt, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockItem, response.getBody());
    }

    @Test
    void removeItemFromWishList() throws ProductException {
        String jwt = "Bearer tokenkdsjkslajkasjdksd.kafsddssdfjk.jksfdsdjklf";
        String email = "rohan@gmail.com";
        int id = 1;
        when(jwtMethods.getEmailFromToken(jwt)).thenReturn(email);

        String mockResponse = "Item removed successfully";
        when(customerService.deleteWishListItem(email, id)).thenReturn(mockResponse);

        ResponseEntity<EcommerceResponse> response = wishListController.removeItemFromWishList(jwt, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody().getMessage());

    }
}