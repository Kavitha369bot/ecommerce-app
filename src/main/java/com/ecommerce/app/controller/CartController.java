package com.ecommerce.app.controller;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.service.CartService;
import com.ecommerce.app.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartservice;

    @PostMapping("/addItemtoCart")
    public ResponseEntity<CartItem> addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal ){
        System.out.println("adding item to cart");
        return new ResponseEntity<>(cartservice.addToCart(productId,quantity,principal.getName()), HttpStatus.CREATED);
    }
    @GetMapping("/getCartItems")
    public ResponseEntity<List<CartItem>> getAllItems(Principal principal){
      return new ResponseEntity<>(cartservice.getCartItems(principal),HttpStatus.OK);
    }







}
