package com.kafka.notification_service.service;

import com.kafka.notification_service.model.Order;

public interface EmailService {
    void sendOrderConfirmation(Order order);
}
