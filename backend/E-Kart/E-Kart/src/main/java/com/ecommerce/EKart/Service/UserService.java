package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;

public interface UserService{
    User findUserById(int userId) throws UserNotFoundException;
    User findUserByJwt(String jwt) throws UserNotFoundException;
    void createNewUser(User user);
}
