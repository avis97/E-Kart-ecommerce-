package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Dtos.Request.AddItemRequest;
import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;

public interface CartService{
    Cart createCart(User user);
    String addCartItem(int userId, AddItemRequest addItemRequest) throws ProductNotFoundException, UserNotFoundException;
    Cart findCartByUserId(int userId);
}
