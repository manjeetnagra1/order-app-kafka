package com.kafka.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private int status;
    private String message;

}
