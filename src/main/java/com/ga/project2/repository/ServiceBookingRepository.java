package com.ga.project2.repository;

import com.ga.project2.model.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {

    List<ServiceBooking> findByUserId(Long userId);
    List<ServiceBooking> findByProviderId(Long providerId);
    List<ServiceBooking> findByServiceId(Long serviceId);

    // Active filters
    List<ServiceBooking> findByActiveTrue();
    List<ServiceBooking> findByServiceIdAndActiveTrue(Long serviceId);
    List<ServiceBooking> findByProviderIdAndActiveTrue(Long providerId);
    List<ServiceBooking> findByUserIdAndActiveTrue(Long userId);

    Optional<ServiceBooking> findServiceBookingByBookingIdAndUserId(Long bookingId, Long userId);

}