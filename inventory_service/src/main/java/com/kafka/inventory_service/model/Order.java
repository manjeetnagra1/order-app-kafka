package com.kafka.inventory_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "product")
    private String product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private String status;
    @Column(name = "address")
    private String address;
}