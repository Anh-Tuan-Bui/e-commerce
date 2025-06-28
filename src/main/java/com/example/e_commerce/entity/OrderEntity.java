package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    UserEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItemEntity> items = new ArrayList<>();
    BigDecimal totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;
}
