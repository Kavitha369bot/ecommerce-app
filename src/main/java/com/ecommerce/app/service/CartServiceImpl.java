package com.ecommerce.app.service;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartItem addToCart(Long productId, int quantity, String username) {
      Product product=  productRepository.findById(productId).orElseThrow();
      CartItem cart=new CartItem();
      cart.setQuantity(quantity);
      cart.setProduct(product);
      cart.setUsername(username);
      CartItem cartItem= cartRepository.save(cart);
      return  cartItem;
    }

    @Override
    public List<CartItem> getCartItems(Principal principal) {
     return cartRepository.findByUsername(principal.getName());
    }

}
