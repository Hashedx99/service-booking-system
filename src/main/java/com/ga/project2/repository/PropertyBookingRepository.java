package com.ga.project2.repository;

import com.ga.project2.model.PropertyBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyBookingRepository extends JpaRepository<PropertyBooking,Long> {
    List<PropertyBooking> findByProperty_propertyId(Long propertyId);
    List<PropertyBooking> findByActiveTrue();
}
