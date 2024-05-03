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
        User user=userRepository.findById(userId).get();
        return user;
    }

    @Override
    public User findUserByJwt(String jwt) throws UserNotFoundException {
        User user=userRepository.findFirstByJwt(jwt);
        return user;
    }

    public User createNewUser(User user){
        User newUser=new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setNumber(user.getNumber());
        newUser.setCreationDate(LocalDateTime.now());
        return userRepository.save(newUser);
    }
    public void updateToken(String jwt,String email){
        User user=userRepository.findByEmail(email);
        user.setJwt(jwt);
        userRepository.save(user);
    }

}
