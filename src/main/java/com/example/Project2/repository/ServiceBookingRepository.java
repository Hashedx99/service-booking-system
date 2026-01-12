package com.example.Project2.repository;

import com.example.Project2.model.ServiceBooking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ServiceBookingRepository extends CrudRepository<ServiceBooking, Long> {
    @Query("SELECT SB FROM ServiceBooking SB WHERE SB.service.serviceId = :id")
    List<ServiceBooking> findByServiceId(@Param("id") Long serviceId);

    @Query("SELECT SB FROM ServiceBooking SB WHERE SB.provider.id = :id")
    List<ServiceBooking> findByProviderId(@Param("id") Long providerId);

    List<ServiceBooking> findAll();
}
