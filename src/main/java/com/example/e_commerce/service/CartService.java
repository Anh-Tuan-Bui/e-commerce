package com.example.e_commerce.service;

import com.example.e_commerce.dto.request.CartRequest;
import com.example.e_commerce.dto.response.CartResponse;

public interface CartService {
    CartResponse getCurrentUserCart();
    CartResponse addToCart(CartRequest request);
    CartResponse removeFromCart(Long productId);
}
