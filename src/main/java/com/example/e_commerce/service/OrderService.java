package com.example.e_commerce.service;

import com.example.e_commerce.entity.OrderEntity;

public interface OrderService {
    OrderEntity createOrderFromCart(String username);
}
