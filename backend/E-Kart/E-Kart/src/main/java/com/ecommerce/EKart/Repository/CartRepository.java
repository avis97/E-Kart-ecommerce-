package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    @Query("Select c from Cart c Where c.user.id=:userId")
    Cart findByUserId(@Param("userId") int userId);
}
