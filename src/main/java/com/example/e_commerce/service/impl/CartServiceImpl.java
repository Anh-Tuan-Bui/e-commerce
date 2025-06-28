package com.example.e_commerce.service.impl;

import com.example.e_commerce.dto.request.CartRequest;
import com.example.e_commerce.dto.response.CartResponse;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartResponse getCurrentUserCart() {

        return null;
    }

    @Override
    public CartResponse addToCart(CartRequest request) {
        return null;
    }

    @Override
    public CartResponse removeFromCart(Long productId) {
        return null;
    }
}
