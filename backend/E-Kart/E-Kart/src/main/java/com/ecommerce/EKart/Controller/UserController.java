package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Repository.UserRepository;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> getUserByJwt(@RequestHeader("Authorization") String jwt)
            throws UserNotFoundException {
        String newJwt=jwt.substring(7);

        User user=userService.findUserByJwt(newJwt);
        System.out.println(user+" "+newJwt);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
    @GetMapping("/profile/{userId}")
    public User findUserById(@PathVariable("userId") int userId) throws UserNotFoundException {
        User user=userService.findUserById(userId);
        return user;
    }

}
