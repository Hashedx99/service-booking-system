package com.ga.project2.service;

import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.exception.MissingFieldException;
import com.ga.project2.model.Property;
import com.ga.project2.model.PropertyBooking;
import com.ga.project2.repository.PropertyBookingRepository;
import com.ga.project2.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyBookingService {
    private final PropertyBookingRepository propertyBookingRepository;
    private final PropertyRepository propertyRepository;


    public PropertyBookingService(PropertyBookingRepository propertyBookingRepository, PropertyRepository propertyRepository) {
        this.propertyBookingRepository = propertyBookingRepository;
        this.propertyRepository = propertyRepository;
    }

    public PropertyBooking createBooking(LocalDate bookingDate, Long userId, Long propertyId) {
        if (bookingDate == null || userId == null || propertyId == null) {
            throw new MissingFieldException("Booking date, user ID, and property ID must not be null");
        }
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        PropertyBooking booking = new PropertyBooking();
        booking.setBookingDate(bookingDate);
        booking.setUserId(userId);
        booking.setProperty(property);

        return propertyBookingRepository.save(booking);
    }

    public Optional<PropertyBooking> getBooking(Long id) {
        return propertyBookingRepository.findById(id);
    }

    public List<PropertyBooking> getAllBookings() {
        return propertyBookingRepository.findAll();
    }

    // Update booking date
    public PropertyBooking updateBookingDate(Long bookingId, LocalDate newDate) {
        PropertyBooking booking = getBooking(bookingId)
                .orElseThrow(() -> new InformationNotFoundException("Booking with id " + bookingId + " not found"));
        booking.setBookingDate(newDate);
        return propertyBookingRepository.save(booking);
    }

    // Soft delete
    public void softDeleteBooking(Long id) {
        PropertyBooking booking = getBooking(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        booking.setActive(false);
        propertyBookingRepository.save(booking);
    }

    // Get only active bookings
    public List<PropertyBooking> getActiveBookings() {
        return propertyBookingRepository.findByActiveTrue();
    }

    public List<PropertyBooking> getBookingsByPropertyId(Long propertyId) {
        List<PropertyBooking> bookings = propertyBookingRepository.findByProperty_propertyId(propertyId);
        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("No bookings found for property id: " + propertyId);
        }
        return bookings;
    }

}