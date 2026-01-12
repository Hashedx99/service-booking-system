package com.example.Project2.service;

import com.example.Project2.model.ServiceBooking;
import com.example.Project2.model.request.CreateServiceBookingRequest;
import com.example.Project2.repository.ServiceBookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceBookingService {
    private final UserService userService;
    private final ServiceService serviceService;
    private final ServiceBookingRepository bookingRepository;

    // function to fetch the service booking results
    private ServiceBooking getBookingRecord(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // CREATE
    public ServiceBooking create(CreateServiceBookingRequest request) {
        // get the service by id
        var service = serviceService.getServiceById(request.getServiceId());

        // get the user by id
        var provider = userService.getUserById(request.getProviderId());

        // create booking instance
        var booking = new ServiceBooking();

        // fill the required fields
        booking.setBookingDate(Instant.parse(request.getBookingDate()));
        booking.setService(service);
        booking.setProvider(provider);

        // save and return the created record
        return bookingRepository.save(booking);
    }

    // READ ONE
    public ServiceBooking getById(Long id) {
        // return the service booking record
        return getBookingRecord(id);
    }

    // READ ALL
    public List<ServiceBooking> getAll() {
        // return list of service booking records
        return bookingRepository.findAll();
    }

    // READ BY SERVICE
    public List<ServiceBooking> getByService(Long serviceId) {
        // return list of booking records by service id
        return bookingRepository.findByServiceId(serviceId);
    }

    // READ BY PROVIDER
    public List<ServiceBooking> getByProvider(Long providerId) {
        // return list of booking records by provider id
        return bookingRepository.findByProviderId(providerId);
    }

    // UPDATE
    public ServiceBooking update(Long id, Instant bookingDate) {
        // get the booking record
        var booking = getBookingRecord(id);

        // update the booking record
        booking.setBookingDate(bookingDate);

        // return the updated record
        return bookingRepository.save(booking);
    }

    // SOFT DELETE
    public void deactivate(Long id) {
        // get the booking record
        var booking = getBookingRecord(id);

        // deactivate the record
        booking.setIsActive(false);

        // return the updated record
        bookingRepository.save(booking);
    }
}
