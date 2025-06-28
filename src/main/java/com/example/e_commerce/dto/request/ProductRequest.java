package com.example.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    @NotBlank(message = "Product's name is required")
    String name;

    String description;

    @NotBlank(message = "Price is required")
    BigDecimal price;

    @NotBlank(message = "Quantity in stock is required")
    Integer quantity;

    @NotBlank(message = "Images of product is required")
    List<String> images;
}
