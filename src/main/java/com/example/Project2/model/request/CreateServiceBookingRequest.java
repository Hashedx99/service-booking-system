package com.example.Project2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class CreateServiceBookingRequest {
    private Long serviceId; // service id
    private Long providerId; // user id
    private String bookingDate; // instant
}
