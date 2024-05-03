package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer>{
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> getAllProductRatings(@Param("productId") int productId);
}
