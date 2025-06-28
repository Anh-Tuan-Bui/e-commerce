package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shops")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;

    @OneToOne
    UserEntity owner;

    @OneToMany
    List<ProductEntity> products = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;
}
