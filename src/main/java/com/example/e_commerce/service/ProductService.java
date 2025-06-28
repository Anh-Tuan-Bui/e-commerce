package com.example.e_commerce.service;

import com.example.e_commerce.dto.request.ProductRequest;
import com.example.e_commerce.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest request);
}
