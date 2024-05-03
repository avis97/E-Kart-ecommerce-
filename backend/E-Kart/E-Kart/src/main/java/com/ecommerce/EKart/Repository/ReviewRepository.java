package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{

    @Query("Select r from Review r Where r.product.id=:productId")
    List<Review> getAllProductReview(@Param("productId") int productId);
}
