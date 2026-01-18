package com.ga.project2.model.request;

import lombok.Getter;

import java.time.LocalDate;


public class CreatePropertyBooking {
    private LocalDate bookingDate;
    private Long userId;
    private Long propertyId;

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }
}