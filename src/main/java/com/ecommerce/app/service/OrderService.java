package com.ecommerce.app.service;

import com.ecommerce.app.entity.Orders;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    public Orders placeOrder(String username);
    public List<Orders> getAllOrders(String username);
    public Orders getOrdersById(Long id);
    public List<Orders> getAllOrders();
    Page<Orders> getOrdersBypage(Pageable pageable);

}
