package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.OrderStatus;
import com.example.ecommerce.entity.Orders;
import com.example.ecommerce.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Orders placeOrder(Orders order) {
    	order.setStatus(OrderStatus.PLACED);
        return repository.save(order);
    }

    public List<Orders> getUserOrders(Long userId) {
        return repository.findByUserId(userId);
    }
}
