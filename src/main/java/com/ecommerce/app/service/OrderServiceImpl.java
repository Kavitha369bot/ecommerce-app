package com.ecommerce.app.service;

import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.entity.OrderItem;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    @Override
    public Orders placeOrder(String username) {
        List<CartItem> cartItems = cartRepository.findByUsername(username);
        List<OrderItem> orderItems = new ArrayList<>();
        Orders order = new Orders();
        order.setUsername(username);
        order.setOrderDate(LocalDate.now());
        for(CartItem item:cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(item.getProduct().getPrice());
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrders(order);
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        Orders savedOrder= orderRepository.save(order);
        cartRepository.deleteByUsername(username);
        return savedOrder;
    }

    @Override
    public List<Orders> getAllOrders(String username) {
        List<Orders> orders=orderRepository.findByUsername(username);
        return orders;
    }

    @Override
    public Orders getOrdersById(Long id) {
       return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
    }

    @Override
    public List<Orders> getAllOrders() {
      return orderRepository.findAll();

    }

    @Override
    public Page<Orders> getOrdersBypage(Pageable pageable) {
       return orderRepository.findAll(pageable);
    }

}
