package com.ga.project2.model.request;

import java.time.LocalDate;

public class UpdatePropertyBookingDate {
    private Long Id;
    private LocalDate NewDate;

    public Long getId() {
        return Id;
    }

    public LocalDate getNewDate() {
        return NewDate;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setNewDate(LocalDate newDate) {
        NewDate = newDate;
    }
}