package com.kafka.inventory_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.inventory_service.model.Order;
import com.kafka.inventory_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final OrderRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public InventoryService(OrderRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processInventory(Order order) throws JsonProcessingException {
        try {
            Order savedOrder = repository.save(order);
            String message = objectMapper.writeValueAsString(savedOrder);
            kafkaTemplate.send("notification-events", message);
            log.info("Sent inventory event to Kafka: {}", message);
        } catch (Exception e) {
            log.error("Failed to serialize inventory event", e);
            throw e;
        }
    }
}
