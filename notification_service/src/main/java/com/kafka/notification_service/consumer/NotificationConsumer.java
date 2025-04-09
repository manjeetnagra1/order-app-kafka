package com.kafka.notification_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.notification_service.model.Order;
import com.kafka.notification_service.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public NotificationConsumer(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "notification-events", groupId = "notification-group")
    public void notificationConsumer(String message) {
        try {
            Order order = objectMapper.readValue(message, Order.class);
            log.info("Received notification event: {}", order);
            emailService.sendOrderConfirmation(order);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize message: {}", message, e);
        } catch (Exception e) {
            log.error("Unexpected error while processing message: {}", message, e);
        }
    }
}
