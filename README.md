# Kafka-Based Order Processing System

A simple microservices-based application built with Spring Boot that demonstrates asynchronous communication using Apache Kafka. It simulates an e-commerce order flow where users can place orders, saved the order in database, and email notifications are sent.

## Services Overview
This project is divided into three microservices


### 1. **Order Service**
- Accepts order placement requests.
- Sends order details to Kafka topic `order-events`.

### 2. **Inventory Service**
- Listens to `order-events` topic.
- Saves the order in database in **Mysql**
- Publishes an event to `notification-events` topic after processing.

### 3. **Notification Service**
- Listens to `notification-events` topic.
- Sends a confirmation email to the user (mocked/logged).


## Tech Stack
- Java 17+
- Spring Boot 3.x
- Apache Kafka
- Spring Kafka
- Spring Data JPA (MySQL)
- Lombok
- Postman (for testing)


## Kafka Topics

| Topic Name           | Description                          |
|----------------------|--------------------------------------|
| `order-events`       | Produced by Order Service            |
| `notification-events`| Produced by Inventory Service        |


## Sample Payload (Place Order)
{
  "product": "iPhone 15",
  "quantity": 1,
  "email": "manjeetsinghwins@gmail.com",
  "address": "D140 7 phase Mohali"
}
