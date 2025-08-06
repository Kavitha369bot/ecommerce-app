package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<Orders> placeOrder(@RequestParam String username){
      return new ResponseEntity<>(orderService.placeOrder(username), HttpStatus.CREATED);
    }
    @GetMapping("/getuserOrders")
    public ResponseEntity<List<Orders>> getAllOrders(@RequestParam String username){
       return new ResponseEntity<>(orderService.getAllOrders(username),HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Orders> getOrdersById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrdersById(id),HttpStatus.FOUND);
    }

    @GetMapping("/api/admin/getAllOrders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/getAllOrdesbypage")
    public Page<Orders> getAllbypage(Pageable pageable){
        return orderService.getOrdersBypage(pageable);
    }

}
