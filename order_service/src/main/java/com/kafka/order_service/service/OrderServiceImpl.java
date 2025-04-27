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
            log.info("Preparing to send order event. Order ID: {}, Order details: {}", order.getOrderId(), json);
            kafkaTemplate.send("order-events", json);
            log.info("Successfully sent order event to 'order-events' topic. Order ID: {}", order.getOrderId());
        }catch (JsonProcessingException e) {
            log.error("Failed to serialize Order to JSON. Order ID: {}, Error: {}", order.getOrderId(), e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while sending order event. Order ID: {}, Error: {}", order.getOrderId(), e.getMessage(), e);
            throw e;
        }
    }

}
