package com.ecommerce.EKart.Service.Impl;


import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Repository.UserRepository;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(int userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public User findUserByJwt(String jwt) throws UserNotFoundException {
        return null;
    }

    public void createNewUser(User user){
        User newUser=new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setNumber(user.getNumber());
        newUser.setCreationDate(LocalDateTime.now());
        userRepository.save(newUser);
    }

}
