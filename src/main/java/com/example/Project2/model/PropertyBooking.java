package com.example.Project2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "property_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDate bookingDate;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false) // FK to Property
    private Property property;

    private boolean active;
}
