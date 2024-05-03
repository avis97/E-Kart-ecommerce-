package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Dtos.Request.AddItemRequest;
import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.CartItem;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Repository.CartItemRepository;
import com.ecommerce.EKart.Repository.CartRepository;
import com.ecommerce.EKart.Service.CartItemService;
import com.ecommerce.EKart.Service.CartService;
import com.ecommerce.EKart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ProductService productService;

    @Override
    public Cart createCart(User user){
       Cart cart=new Cart();
       cart.setUser(user);
       return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(int userId, AddItemRequest addItemRequest) throws ProductNotFoundException, UserNotFoundException {

        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(addItemRequest.getProductId());
        CartItem cartItemIsPresent=cartItemService.isCartItemExist(cart,product,addItemRequest.getSize(),userId);
        if(cartItemIsPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(addItemRequest.getQuantity());
            cartItem.setUserId(userId);

            int price=addItemRequest.getQuantity()*product.getDiscountPrice();
            cartItem.setPrice(price);
            cartItem.setSize(addItemRequest.getSize());

            CartItem createCartItem=cartItemService.createCartItem(cartItem);
            cart.getCartItemList().add(createCartItem);
        }
        return "Item Add To Cart";
    }

    @Override
    public Cart findCartByUserId(int userId) {
        Cart cart=cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDiscountPrice=0;
        int totalItem=0;
        for(CartItem cartItem:cart.getCartItemList()){
            totalPrice=totalPrice+ cartItem.getPrice();
            totalDiscountPrice=totalDiscountPrice+cartItem.getDiscountPrice();
            totalItem=totalItem+cartItem.getQuantity();
        }
        cart.setTotalDiscountPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountPrice);

        return cartRepository.save(cart);

    }
}
