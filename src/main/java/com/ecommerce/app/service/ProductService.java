package com.ecommerce.app.service;

import com.ecommerce.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product addProduct(Product product) ;
    public Product getProductById(Long id) ;
}
