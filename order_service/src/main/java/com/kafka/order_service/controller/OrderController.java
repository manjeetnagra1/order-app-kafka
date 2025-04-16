package com.kafka.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.order_service.dto.ResponseDTO;
import com.kafka.order_service.model.Order;
import com.kafka.order_service.service.OrderService;
import com.kafka.order_service.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService service;

    public OrderController(OrderServiceImpl service) {
        this.service = service;
    }

    ResponseDTO responseDTO = new ResponseDTO();

    @PostMapping
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody Order order) throws JsonProcessingException {
        try {
            order.setStatus("Order Created");
            service.sendOrderEvent(order);
            responseDTO.setStatus(200);
            responseDTO.setMessage("Order Placed you will receive a email once its confirmed");

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            logger.error("Internal Server Error: {}", e.getMessage(), e);
            responseDTO.setMessage("Internal Server Error");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }
}

