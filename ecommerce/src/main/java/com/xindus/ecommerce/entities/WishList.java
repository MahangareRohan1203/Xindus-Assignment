package com.xindus.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishListId;

    @OneToOne
    private Customers customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "wishList")
    private List<WishListItem> wishListItems = new ArrayList<>();
}
