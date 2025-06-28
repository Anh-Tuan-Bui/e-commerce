package com.example.e_commerce.service.impl;

import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductResponse;
import com.example.e_commerce.entity.ProductEntity;
import com.example.e_commerce.exception.NotFoundException;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductResponse.class))
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        return modelMapper.map(productEntity, ProductResponse.class);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductEntity savedProduct = productRepository.save(modelMapper.map(request, ProductEntity.class));
        return modelMapper.map(savedProduct, ProductResponse.class);
    }
}
