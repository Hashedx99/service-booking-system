package com.example.Project2.controller;

import com.example.Project2.model.ServiceBooking;
import com.example.Project2.model.request.CreateServiceBookingRequest;
import com.example.Project2.model.response.ServiceBookingResponse;
import com.example.Project2.repository.ServiceBookingRepository;
import com.example.Project2.service.ServiceBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/service-bookings")
public class ServiceBookingController {
    private final ServiceBookingRepository serviceBookingRepository;

    private final ServiceBookingService bookingService;

    // CREATE
    @PostMapping
    public ResponseEntity<ServiceBooking> create(
            @RequestBody CreateServiceBookingRequest request
    ) {
        // return the created entity
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.create(request));
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ServiceBooking> getById(@PathVariable Long id) {
        // return the single booking record by id
        return ResponseEntity.ok(bookingService.getById(id));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ServiceBooking>> getAll() {
        // return list of booking records of the system
        return ResponseEntity.ok(bookingService.getAll());
    }

    // READ BY SERVICE
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceBooking>> getByService(
            @PathVariable Long serviceId
    ) {
        // return list of booking records by service id
        return ResponseEntity.ok(bookingService.getByService(serviceId));
    }

    // READ BY PROVIDER
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ServiceBooking>> getByProvider(
            @PathVariable Long providerId
    ) {
        // return list of booking records by provider id
        return ResponseEntity.ok(bookingService.getByProvider(providerId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ServiceBooking> update(
            @PathVariable Long id,
            @RequestParam Instant bookingDate
    ) {
        // return the updated entity
        return ResponseEntity.ok(
                bookingService.update(id, bookingDate)
        );
    }

    // DEACTIVATE (RESTFUL SOFT DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        // deactivate the booking
        bookingService.deactivate(id);

        // return 204 response
        return ResponseEntity.noContent().build();
    }

}
