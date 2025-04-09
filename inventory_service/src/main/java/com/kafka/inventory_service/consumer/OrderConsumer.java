package com.kafka.inventory_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.inventory_service.model.Order;
import com.kafka.inventory_service.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);
    private final InventoryService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public OrderConsumer(InventoryService service) {
        this.service = service;
    }

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consumeOrder(String message)  {
        try {
            Order event = mapper.readValue(message, Order.class);
            log.info("Order Received: {}", event);
            service.processInventory(event);
        }catch (Exception e){
            log.error("Unexpected error consuming message: {}", message, e);
        }

    }
}
