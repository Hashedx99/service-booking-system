package com.example.Project2.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ServiceBookingResponse {
    private Long bookingId;
    private Instant bookingDate;
    private Boolean isActive;

}
