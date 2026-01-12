package com.example.Project2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "service_bookings")
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date", nullable = false)
    private Instant bookingDate;

    // FK's
    @JoinColumn(name = "service_id")
    private Long serviceId;

    @JoinColumn(name = "provider_id")
    private Long providerId;

    @JoinColumn(name = "user_id")
    private Long userId;

    @Column(name = "active", nullable = false)
    private  boolean active;
}
