package com.example.Project2.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "property_schedules")
public class PropertySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;   // PK

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false) // FK to Property
    private Property property;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
