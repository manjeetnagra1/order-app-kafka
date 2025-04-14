package com.kafka.order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order_service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public OrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Order order) {
        try {
            String json = mapper.writeValueAsString(order);
            kafkaTemplate.send("order-events", json);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: {}", e.getMessage(), e);
        }
    }

}
