package com.ga.project2.controller;

import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.model.PropertyBooking;
import com.ga.project2.service.PropertyBookingService;
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
    public PropertyBooking createBooking(@RequestBody CreatePropertyBooking model) {
        return propertyBookingService.createBooking(model.getBookingDate(), model.getUserId(), model.getPropertyId());
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public PropertyBooking getBooking(@PathVariable Long id) {
        return propertyBookingService.getBooking(id).orElseThrow(()-> new InformationNotFoundException("Booking with ID "+id+" not found"));
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
    public PropertyBooking updateBookingDate(@RequestBody UpdateBookingDate model) {
        return propertyBookingService.updateBookingDate(model.getId(), model.getNewDate());

    }

    // Soft delete booking
    @DeleteMapping("/{id}")
    public void softDeleteBooking(@PathVariable Long id) {
        propertyBookingService.softDeleteBooking(id);
    }

    // Get only active bookings
    @GetMapping("/active")
    public List<PropertyBooking> getActiveBookings() {
        return propertyBookingService.getActiveBookings();
    }
}