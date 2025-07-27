package com.ecommerce.app.service;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.entity.Product;

import java.security.Principal;
import java.util.List;

public interface CartService {
    public CartItem addToCart(Long productId,
                              int quantity,
                             String username);
    public List<CartItem> getCartItems(Principal principal);
}
