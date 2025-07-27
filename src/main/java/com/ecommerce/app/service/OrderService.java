package com.ecommerce.app.service;

import com.ecommerce.app.entity.Orders;

import java.util.List;

public interface OrderService {
    public Orders placeOrder(String username);
    public List<Orders> getAllOrders(String username);
}
