package com.ga.project2.controller;

import com.ga.project2.model.ServiceBooking;
import com.ga.project2.model.request.CreateServiceBookingRequest;
import com.ga.project2.model.request.UpdateBookingDate;
import com.ga.project2.service.ServiceBookingService;
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
    public ServiceBooking createBooking(
        @RequestBody CreateServiceBookingRequest model) {
        return serviceBookingService.createBooking(
                model.getBookingDate()
                ,model.getServiceId()
                ,model.getProviderId()
                ,model.getUserId());
    }

    @GetMapping("/{id}")
    public ServiceBooking getBooking(@PathVariable Long id) {
        return serviceBookingService.getBooking(id);
    }

    @GetMapping
    public List<ServiceBooking> getAllBookings() {
        return serviceBookingService.getAllBookings();
    }

    @GetMapping("/user/{userId}")
    public List<ServiceBooking> getBookingsByUser(@PathVariable Long userId) {
        return serviceBookingService.getBookingsByUser(userId);
    }

    @GetMapping("/provider/{providerId}")
    public List<ServiceBooking> getBookingsByProvider(@PathVariable Long providerId) {
        return serviceBookingService.getBookingsByProvider(providerId);
    }

    @GetMapping("/service/{serviceId}")
    public List<ServiceBooking> getBookingsByService(@PathVariable Long serviceId) {
        return serviceBookingService.getBookingsByService(serviceId);
    }

    @PutMapping("/date")
    public ServiceBooking updateBookingDate(@RequestBody UpdateBookingDate model) {
        return serviceBookingService.updateBookingDate(model.getId(), Instant.from(model.getNewDate().atStartOfDay()));
    }

    @DeleteMapping("/{id}")
    public void softDeleteBooking(@PathVariable Long id) {
        serviceBookingService.softDeleteBooking(id);
    }

    @GetMapping("/active")
    public List<ServiceBooking> getActiveBookings() {
        return serviceBookingService.getActiveBookings();
    }

    @GetMapping("/active/user/{userId}")
    public List<ServiceBooking> getActiveBookingsByUser(@PathVariable Long userId) {
        return serviceBookingService.getActiveBookingsByUser(userId);
    }

    @GetMapping("/active/provider/{providerId}")
    public List<ServiceBooking> getActiveBookingsByProvider(@PathVariable Long providerId) {
        return serviceBookingService.getActiveBookingsByProvider(providerId);
    }

    @GetMapping("/active/service/{serviceId}")
    public List<ServiceBooking> getActiveBookingsByService(@PathVariable Long serviceId) {
        return serviceBookingService.getActiveBookingsByService(serviceId);
    }

    @PostMapping("/cancel/{id}")
    public ServiceBooking cancelBooking(@PathVariable Long id) {
        return serviceBookingService.cancelBooking(id);
    }
}