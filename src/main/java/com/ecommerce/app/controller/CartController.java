package com.ecommerce.app.controller;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.service.CartService;
import com.ecommerce.app.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartservice;

    @PostMapping("/addItemtoCart")
    public CartItem addToCart(@RequestParam Long productId,@RequestParam int quantity, Principal principal ){
        System.out.println("adding item to cart");
        return cartservice.addToCart(productId,quantity,principal.getName());
    }
    @GetMapping("/getCartItems")
    public List<CartItem> getAllItems(Principal principal){
      return cartservice.getCartItems(principal);
    }







}
