package com.ga.project2.repository;

import com.ga.project2.model.PropertyBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyBookingRepository extends JpaRepository<PropertyBooking,Long> {
    List<PropertyBooking> findByProperty_propertyId(Long propertyId);
    List<PropertyBooking> findByActiveTrue();
    Optional<PropertyBooking> getPropertyBookingByBookingIdAndUserId(Long bookingId, Long userId);
}
