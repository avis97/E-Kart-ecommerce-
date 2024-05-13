package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.CartItem;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Exception.UserNotFoundException;

public interface CartItemService{
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(int cartId,int qu) throws Exception;
    CartItem isCartItemExist(Cart cart, Product product,String size,int userId) throws UserNotFoundException;
    String removeCartItem(int userId,int cartItemId) throws Exception;
    CartItem findCartItemById(int cartItemId) throws Exception;
}
