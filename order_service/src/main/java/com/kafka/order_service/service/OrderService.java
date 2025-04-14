package com.kafka.order_service.service;

import com.kafka.order_service.model.Order;

public interface OrderService {
    void sendOrderEvent(Order order);
}
