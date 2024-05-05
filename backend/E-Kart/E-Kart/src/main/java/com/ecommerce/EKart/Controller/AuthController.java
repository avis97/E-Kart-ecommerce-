package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Config.Security.JwtServiceImpl;
import com.ecommerce.EKart.Config.Security.UserDetailsServiceImpl;
import com.ecommerce.EKart.Dtos.Request.AuthRequest;
import com.ecommerce.EKart.Dtos.Response.AuthResponse;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Repository.UserRepository;
import com.ecommerce.EKart.Service.CartService;
import com.ecommerce.EKart.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController{
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtServiceImpl jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    CartService cartService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createNewUser(@RequestBody User user) throws UserNotFoundException{

        String email=user.getEmail();
        String password=user.getPassword();
        User user1=userRepository.findByEmail(email);
        AuthResponse response=new AuthResponse();
        if(user1!=null){
            response.setMsg("This email have a account!!");
            response.setJwt("Opps!! cant Possible");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        User saveUser=userService.createNewUser(user);
        cartService.createCart(saveUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtService.GenerateToken(email);
        userService.updateToken(token,email);
        response.setJwt(token);
        response.setMsg("Signup success!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request){
        String username=request.getEmail();
        String password= request.getPassword();
        AuthResponse response=new AuthResponse();
        Authentication authentication = null;
        try {
            authentication = authenticate(username,password);
        }catch (Exception e){
            response.setMsg("Wrong Password or Email");
            response.setJwt("error");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtService.GenerateToken(username);
        userService.updateToken(token,username);
        response.setJwt(token);
        response.setMsg("Login Success");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String username, String password){

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Username not verify!!");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password not verify!!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,userDetails.getAuthorities());
    }


}
