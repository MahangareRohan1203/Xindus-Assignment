package com.xindus.ecommerce.repositories;

import com.xindus.ecommerce.entities.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Integer> {
    public Optional<WishListItem> findByProduct_productIdAndWishList_wishListId(Integer productId, Integer wishListId);

    @Modifying
    @Query(value = "DELETE FROM WishListItem WHERE id=?1")
    public void deleteById(Integer id);
}
