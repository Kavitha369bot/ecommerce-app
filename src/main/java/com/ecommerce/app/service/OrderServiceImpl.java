package com.ecommerce.app.service;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.entity.OrderItem;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public Orders placeOrder(String username) {
        List<CartItem> cartItems = cartRepository.findByUsername(username);
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem item:cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(item.getProduct().getPrice());
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
        }
        Orders order = new Orders();
        order.setUsername(username);
        order.setOrderDate(LocalDate.now());
        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders(String username) {
        List<Orders> orders=orderRepository.findByUsername(username);
        return orders;
    }
}
