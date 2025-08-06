package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.service.ProductService;
import com.ecommerce.app.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product),HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getproductbyId(@PathVariable Long id){
        System.out.println("getting product by id"+id);
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

}
