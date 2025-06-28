package com.example.e_commerce.service;

import com.example.e_commerce.dto.request.LoginRequest;
import com.example.e_commerce.dto.request.RegisterRequest;
import com.example.e_commerce.dto.response.LoginResponse;
import com.example.e_commerce.dto.response.UserResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    UserResponse register(RegisterRequest request);
}
