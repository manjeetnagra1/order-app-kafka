package com.kafka.notification_service.service;

import com.kafka.notification_service.consumer.NotificationConsumer;
import com.kafka.notification_service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(Order order) {
        String emailContent = String.format(
                "Hello %s,\n\nYour order for '%s' (Quantity: %d) has been confirmed.\nOrder ID: %s\nStatus: %s\n\nThank you!",
                order.getEmail(), order.getProduct(), order.getQuantity(), order.getOrderId(), order.getStatus()
        );

        log.info("Sending email to {}:\n{}", order.getEmail(), emailContent);
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("Order confirmed");
                message.setTo(order.getEmail());
                message.setText(emailContent);
                mailSender.send(message);
            }catch (Exception e){
                log.error("Failed to send email to {}", order.getEmail(), e);
            }
        }
}
