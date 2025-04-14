package com.kafka.inventory_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.inventory_service.model.Order;

public interface InventoryService {
    void processInventory(Order event) throws JsonProcessingException;
}
