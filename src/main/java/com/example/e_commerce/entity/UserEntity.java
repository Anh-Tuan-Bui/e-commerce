package com.example.e_commerce.entity;

import com.example.e_commerce.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;
    @Column(nullable = false)
    String password;

    String name;
    String address;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    CartEntity cart;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    ShopEntity shop;

    LocalDateTime createdAt;
    LocalDateTime lastPasswordChangeAt;
}
