package com.example.Project2.repository;

import com.example.Project2.model.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {

    List<ServiceBooking> findByUserId(Long userId);
    List<ServiceBooking> findByProviderId(Long providerId);
    List<ServiceBooking> findByServiceId(Long serviceId);

    // Active filters
    List<ServiceBooking> findByActiveTrue();
    List<ServiceBooking> findByServiceIdAndActiveTrue(Long serviceId);
    List<ServiceBooking> findByProviderIdAndActiveTrue(Long providerId);
    List<ServiceBooking> findByUserIdAndActiveTrue(Long userId);

}
