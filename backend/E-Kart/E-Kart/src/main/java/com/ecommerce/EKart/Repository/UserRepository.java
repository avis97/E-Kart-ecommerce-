package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    User findFirstByJwt(String jwt);
}
