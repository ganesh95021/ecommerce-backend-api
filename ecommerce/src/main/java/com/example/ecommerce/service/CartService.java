package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public Cart addToCart(Cart cart) {
        return repository.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {
        return repository.findByUserId(userId);
    }

    public void removeCart(Long id) {
        repository.deleteById(id);
    }
}