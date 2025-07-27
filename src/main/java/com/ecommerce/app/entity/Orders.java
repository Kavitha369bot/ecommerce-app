package com.ecommerce.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;
    private LocalDate orderDate;
    @OneToMany (cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
