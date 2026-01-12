package com.example.Project2.controller;

import com.example.Project2.model.PropertyBooking;
import com.example.Project2.service.PropertyBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/property-bookings")
public class PropertyBookingController {

    private final PropertyBookingService propertyBookingService;

    public PropertyBookingController(PropertyBookingService propertyBookingService) {
        this.propertyBookingService = propertyBookingService;
    }

    // Create booking
    @PostMapping
    public ResponseEntity<PropertyBooking> createBooking(
            @RequestParam LocalDate bookingDate,
            @RequestParam Long userId,
            @RequestParam Long propertyId) {
        PropertyBooking booking = propertyBookingService.createBooking(bookingDate, userId, propertyId);
        return ResponseEntity.ok(booking);
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyBooking> getBooking(@PathVariable Long id) {
        return propertyBookingService.getBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Get booking by propertyID
    @GetMapping("/property/{id}")
    public List<PropertyBooking>getBookingByPropertyId(@PathVariable Long id) {
        return propertyBookingService.getBookingsByPropertyId(id);
    }

    // Get all bookings
    @GetMapping
    public List<PropertyBooking> getAllBookings() {
        return propertyBookingService.getAllBookings();
    }

    // Update booking date
    @PutMapping("/{id}/date")
    public ResponseEntity<PropertyBooking> updateBookingDate(
            @PathVariable Long id,
            @RequestParam LocalDate newDate) {
        PropertyBooking updated = propertyBookingService.updateBookingDate(id, newDate);
        return ResponseEntity.ok(updated);
    }

    // Soft delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteBooking(@PathVariable Long id) {
        propertyBookingService.softDeleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    // Get only active bookings
    @GetMapping("/active")
    public List<PropertyBooking> getActiveBookings() {
        return propertyBookingService.getActiveBookings();
    }
}