package com.xindus.ecommerce.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponse {

    private String message;
    private String extraInformation;
    private LocalDateTime timeStamp;
}
