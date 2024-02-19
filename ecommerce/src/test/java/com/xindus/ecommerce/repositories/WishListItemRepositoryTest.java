package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.entities.Products;
import com.xindus.ecommerce.entities.WishList;
import com.xindus.ecommerce.entities.WishListItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WishListItemRepositoryTest {

    @Autowired
    WishListItemRepository wishListItemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WishListRepository wishListRepository;


    @Test
    void findByProduct_productIdAndWishList_wishListId() {


        WishList wishList = new WishList();
        Customers customers = new Customers(1, "email@gmail.com", "name-1", "password", "USER", wishList);
        customers = customerRepository.save(customers);
        wishList.setCustomer(customers);
        wishList = wishListRepository.save(wishList);

        Products product = new Products(1, "Product-1", 100L);
        product = productRepository.save(product);

        WishListItem wishListItem = new WishListItem(1, wishList, product);
        wishListItem = wishListItemRepository.save(wishListItem);

        assertEquals(wishListItemRepository.count(), 1);

        Optional<WishListItem> item1 = wishListItemRepository.findById(1);
        assertTrue(item1.isPresent());

        Optional<WishListItem> item =  wishListItemRepository.findByProduct_productIdAndWishList_wishListId(1, 1);
        assertTrue(item.isPresent());

        wishListItemRepository.deleteById(item1.get().getId());

        assertEquals(wishListItemRepository.count(), 0);
    }


}