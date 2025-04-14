package com.kafka.inventory_service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DLQConsumer {

    @KafkaListener(topics = "order-events-dlq", groupId = "inventory-dlq-group")
    public void dlqConsumer(String message){
        log.warn("Received message in DLQ: {}", message);
    }
}
