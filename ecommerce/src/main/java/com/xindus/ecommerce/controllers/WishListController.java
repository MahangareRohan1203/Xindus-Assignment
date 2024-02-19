package com.xindus.ecommerce.controllers;

import com.xindus.ecommerce.configuration.JwtMethods;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishListItem;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.exceptions.ProductException;
import com.xindus.ecommerce.responses.EcommerceResponse;
import com.xindus.ecommerce.serviceInterfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishListController {

    @Autowired
    private JwtMethods jwtMethods;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<WishListItem>> wishListItems(@RequestHeader("Authorization") String jwt) throws CustomerException {
        String email = jwtMethods.getEmailFromToken(jwt);
        List<WishListItem> list = customerService.getAllProductsFromWishList(email);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<WishListItem> addItemToWishList(@RequestHeader("Authorization") String jwt, @RequestBody Products product) throws ProductException {
        String email = jwtMethods.getEmailFromToken(jwt);
        WishListItem item = customerService.addNewItemToWishList(email, product.getProductId());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EcommerceResponse> removeItemFromWishList(@RequestHeader("Authorization") String jwt, @PathVariable Integer id) throws ProductException {
        String email = jwtMethods.getEmailFromToken(jwt);
        String response = customerService.deleteWishListItem(email, id);
        return new ResponseEntity<>(new EcommerceResponse(response, LocalDateTime.now()), HttpStatus.OK);
    }
}
