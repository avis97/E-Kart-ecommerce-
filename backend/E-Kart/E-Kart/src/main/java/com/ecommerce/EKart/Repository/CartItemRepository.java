package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.CartItem;
import com.ecommerce.EKart.Entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    @Query("Select ci From CartItem ci Where ci.cart=:cart AND ci.product=:product And ci.size=:size And ci.userId=:userId")
    CartItem isCartItemExist(
            @Param("cart")Cart cart,
            @Param("product") Product product,
            @Param("size") String size,
            @Param("userId") int userId);
}
