package com.ga.project2.controller;

import com.ga.project2.model.ServiceBooking;
import com.ga.project2.model.request.CreateServiceBookingRequest;
import com.ga.project2.model.request.UpdateBookingDate;
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
        @RequestBody CreateServiceBookingRequest model) {
        ServiceBooking booking = serviceBookingService.createBooking(
                model.getBookingDate()
                ,model.getServiceId()
                ,model.getProviderId()
                ,model.getUserId());
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

    @PutMapping("/date")
    public ResponseEntity<ServiceBooking> updateBookingDate(@RequestBody UpdateBookingDate model) {
        ServiceBooking updated = serviceBookingService.updateBookingDate(model.getId(), Instant.from(model.getNewDate().atStartOfDay()));
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