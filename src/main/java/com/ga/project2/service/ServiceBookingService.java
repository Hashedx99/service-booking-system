package com.ga.project2.service;

import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.exception.MissingFieldException;
import com.ga.project2.model.ServiceBooking;
import com.ga.project2.repository.ServiceBookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ServiceBookingService {
    private final ServiceBookingRepository serviceBookingRepository;
    private final UserService userService;

    public ServiceBookingService(ServiceBookingRepository serviceBookingRepository, UserService userService) {
        this.serviceBookingRepository = serviceBookingRepository;
        this.userService = userService;
    }

    // Create booking
    public ServiceBooking createBooking(Instant bookingDate, Long serviceId, Long providerId, Long userId) {
        if (bookingDate == null || serviceId == null || providerId == null || userId == null ||
                bookingDate.toString().isEmpty() || serviceId.toString().isEmpty() || providerId.toString().isEmpty() || userId.toString().isEmpty()) {
            throw new MissingFieldException("Booking date, service ID, provider ID, and user ID must not be null");
        }
        ServiceBooking booking = new ServiceBooking();
        booking.setBookingDate(bookingDate);
        booking.setServiceId(serviceId);
        booking.setProviderId(providerId);
        booking.setUserId(userId);
        booking.setActive(true); // default active
        return serviceBookingRepository.save(booking);
    }

    // Get booking by id
    public ServiceBooking getBooking(Long bookingId) {
        return serviceBookingRepository.findById(bookingId)
                .orElseThrow(() -> new InformationNotFoundException("Booking not found with id: " + bookingId));
    }

    // Get all bookings
    public List<ServiceBooking> getAllBookings() {
        return serviceBookingRepository.findAll();
    }

    // Get bookings by user
    public List<ServiceBooking> getBookingsByUser(Long userId) {
        return serviceBookingRepository.findByUserId(userId);
    }

    // Get bookings by provider
    public List<ServiceBooking> getBookingsByProvider(Long providerId) {
        return serviceBookingRepository.findByProviderId(providerId);
    }

    // Get bookings by service
    public List<ServiceBooking> getBookingsByService(Long serviceId) {
        return serviceBookingRepository.findByServiceId(serviceId);
    }

    // Update booking date
    public ServiceBooking updateBookingDate(Long bookingId, Instant newDate) {
        ServiceBooking booking = getBooking(bookingId);
        booking.setBookingDate(newDate);
        return serviceBookingRepository.save(booking);
    }

    // Soft delete
    public void softDeleteBooking(Long bookingId) {
        ServiceBooking booking = getBooking(bookingId);
        booking.setActive(false);
        serviceBookingRepository.save(booking);
    }

    // Get active bookings
    public List<ServiceBooking> getActiveBookings() {
        return serviceBookingRepository.findByActiveTrue();
    }

    // Get active bookings by user
    public List<ServiceBooking> getActiveBookingsByUser(Long userId) {
        return serviceBookingRepository.findByUserIdAndActiveTrue(userId);
    }

    // Get active bookings by provider
    public List<ServiceBooking> getActiveBookingsByProvider(Long providerId) {
        return serviceBookingRepository.findByProviderIdAndActiveTrue(providerId);
    }

    // Get active bookings by service
    public List<ServiceBooking> getActiveBookingsByService(Long serviceId) {
        return serviceBookingRepository.findByServiceIdAndActiveTrue(serviceId);
    }


    public ServiceBooking cancelBooking(Long id) {
        ServiceBooking booking = serviceBookingRepository.findServiceBookingByBookingIdAndUserId(id,
                userService.getUser().getId()).orElseThrow(() -> new InformationNotFoundException("Booking with id " + id + " not found for the current user"));
        booking.setActive(false);
        return serviceBookingRepository.save(booking);
    }
}