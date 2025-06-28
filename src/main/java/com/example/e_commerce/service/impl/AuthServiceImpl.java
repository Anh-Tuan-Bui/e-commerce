package com.example.e_commerce.service.impl;

import com.example.e_commerce.dto.request.LoginRequest;
import com.example.e_commerce.dto.request.RegisterRequest;
import com.example.e_commerce.dto.response.LoginResponse;
import com.example.e_commerce.dto.response.UserResponse;
import com.example.e_commerce.entity.UserEntity;
import com.example.e_commerce.enums.Role;
import com.example.e_commerce.repository.UserRepository;
import com.example.e_commerce.service.AuthService;
import com.example.e_commerce.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    JwtService jwtService;
    ModelMapper modelMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtService.generateToken(user);

        return new LoginResponse(token, modelMapper.map(user, UserResponse.class));
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserEntity savedUser = userRepository.save(UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .lastPasswordChangeAt(LocalDateTime.now())
                .build()
        );

        return modelMapper.map(savedUser, UserResponse.class);
    }
}
