package com.ecommerce.EKart.Service.Impl;


import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.CartItem;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Repository.CartItemRepository;
import com.ecommerce.EKart.Repository.CartRepository;
import com.ecommerce.EKart.Service.CartItemService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    UserService userService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public CartItem createCartItem(CartItem cartItem){

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice()*cartItem.getQuantity());
        CartItem createdCartItem=cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(int cartId,int qu) throws Exception {
        System.out.println("ok");
        CartItem item=findCartItemById(cartId);
        System.out.println("--???"+item.getId());
            item.setQuantity(qu);
            item.setPrice(item.getProduct().getPrice()*item.getQuantity());
            item.setDiscountPrice(item.getProduct().getDiscountPrice()*item.getQuantity());

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, int userId) throws UserNotFoundException {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItem;
    }

    public String removeCartItem(int userId,int cartItemId) throws Exception {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);
        if(user.getId()==reqUser.getId()){
            cartItemRepository.delete(cartItem);
        }else {
            throw new UserNotFoundException("User Not found with this id");
        }
        return "User Delete Successfully done";
    }
    @Override
    public CartItem findCartItemById(int cartItemId) throws Exception {
        CartItem cartItem;
        try{
            cartItem=cartItemRepository.findById(cartItemId).get();
        }catch (Exception e){
            throw new Exception("Cart Item Not Found");
        }
        return cartItem;
    }
}
