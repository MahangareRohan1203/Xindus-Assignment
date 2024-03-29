package com.xindus.ecommerce.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcommerceResponse {
    private String message;
    private LocalDateTime timeStamp;
}
