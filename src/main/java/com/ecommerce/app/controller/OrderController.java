package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public Orders placeOrder(@RequestParam String username){
      return orderService.placeOrder(username);
    }
    @GetMapping("/getuserOrders")
    public List<Orders> getAllOrders(@RequestParam String username){
       return orderService.getAllOrders(username);
    }


}
