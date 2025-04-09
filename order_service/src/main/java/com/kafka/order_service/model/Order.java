package com.kafka.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private String status;
    private String email;
    private String address;
}
