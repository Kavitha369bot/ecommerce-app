package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/getAllProducts")
    public List<Product> getAll() {
        return productServiceImpl.getAllProducts();
    }

    @PostMapping("/saveProduct")
    public Product addProduct(@RequestBody Product product) {
        return productServiceImpl.addProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getproductbyId(@PathVariable Long id){
        System.out.println("getting product by id"+id);
        return productServiceImpl.getProductById(id);
    }

}
