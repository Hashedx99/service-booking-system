package com.ga.project2.controller;

import com.ga.project2.model.ServiceBooking;
import com.ga.project2.service.ServiceBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/service-bookings")
public class ServiceBookingController {

    private final ServiceBookingService serviceBookingService;

    public ServiceBookingController(ServiceBookingService serviceBookingService) {
        this.serviceBookingService = serviceBookingService;
    }

    @PostMapping
    public ResponseEntity<ServiceBooking> createBooking(
            @RequestParam Instant bookingDate,
            @RequestParam Long serviceId,
            @RequestParam Long providerId,
            @RequestParam Long userId) {
        ServiceBooking booking = serviceBookingService.createBooking(bookingDate, serviceId, providerId, userId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBooking> getBooking(@PathVariable Long id) {
        ServiceBooking booking = serviceBookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<List<ServiceBooking>> getAllBookings() {
        return ResponseEntity.ok(serviceBookingService.getAllBookings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ServiceBooking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(serviceBookingService.getBookingsByUser(userId));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ServiceBooking>> getBookingsByProvider(@PathVariable Long providerId) {
        return ResponseEntity.ok(serviceBookingService.getBookingsByProvider(providerId));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceBooking>> getBookingsByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceBookingService.getBookingsByService(serviceId));
    }

    @PutMapping("/{id}/date")
    public ResponseEntity<ServiceBooking> updateBookingDate(
            @PathVariable Long id,
            @RequestParam Instant newDate) {
        ServiceBooking updated = serviceBookingService.updateBookingDate(id, newDate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteBooking(@PathVariable Long id) {
        serviceBookingService.softDeleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceBooking>> getActiveBookings() {
        return ResponseEntity.ok(serviceBookingService.getActiveBookings());
    }

    @GetMapping("/active/user/{userId}")
    public ResponseEntity<List<ServiceBooking>> getActiveBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(serviceBookingService.getActiveBookingsByUser(userId));
    }

    @GetMapping("/active/provider/{providerId}")
    public ResponseEntity<List<ServiceBooking>> getActiveBookingsByProvider(@PathVariable Long providerId) {
        return ResponseEntity.ok(serviceBookingService.getActiveBookingsByProvider(providerId));
    }

    @GetMapping("/active/service/{serviceId}")
    public ResponseEntity<List<ServiceBooking>> getActiveBookingsByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceBookingService.getActiveBookingsByService(serviceId));
    }
}