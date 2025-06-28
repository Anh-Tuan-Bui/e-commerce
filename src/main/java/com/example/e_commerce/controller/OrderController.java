package com.example.e_commerce.controller;

import com.example.e_commerce.entity.OrderEntity;
import com.example.e_commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(Authentication authentication) {
        OrderEntity order = orderService.createOrderFromCart(authentication.getName());

        return ResponseEntity.ok("Order placed successfully with ID: " + order.getId());
    }
}
