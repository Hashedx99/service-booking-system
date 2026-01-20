package com.ga.project2.controller;

import com.ga.project2.model.PropertyBooking;
import com.ga.project2.service.PropertyBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ga.project2.model.request.CreatePropertyBooking;
import com.ga.project2.model.request.UpdateBookingDate;

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
    public ResponseEntity<PropertyBooking> createBooking(@RequestBody CreatePropertyBooking model) {
        PropertyBooking booking = propertyBookingService.createBooking(model.getBookingDate(), model.getUserId(), model.getPropertyId());
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
    public List<PropertyBooking> getBookingByPropertyId(@PathVariable Long id) {
        return propertyBookingService.getBookingsByPropertyId(id);
    }

    // Get all bookings
    @GetMapping
    public List<PropertyBooking> getAllBookings() {
        return propertyBookingService.getAllBookings();
    }

    // Update booking date
    @PutMapping("/date")
    public ResponseEntity<PropertyBooking> updateBookingDate(@RequestBody UpdateBookingDate model) {
        PropertyBooking updated = propertyBookingService.updateBookingDate(model.getId(), model.getNewDate());
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