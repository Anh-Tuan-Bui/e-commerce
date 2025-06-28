package com.example.e_commerce.service.impl;

import com.example.e_commerce.entity.CartEntity;
import com.example.e_commerce.entity.OrderEntity;
import com.example.e_commerce.entity.OrderItemEntity;
import com.example.e_commerce.entity.UserEntity;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.UserRepository;
import com.example.e_commerce.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    UserRepository userRepository;
    CartRepository cartRepository;
    OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderEntity createOrderFromCart(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        CartEntity cart = user.getCart();
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        OrderEntity order = OrderEntity.builder()
                .customer(user)
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItemEntity> orderItems = cart.getItems().stream()
                .map(item -> OrderItemEntity.builder()
                        .order(order)
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .priceAtPurchase(item.getProduct().getPrice())
                        .build())
                .toList();
        order.setItems(orderItems);

        OrderEntity savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }
}
