package com.ga.project2.repository;

import com.ga.project2.model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    Optional<Service> getServicesByServiceIdAndIsActiveTrue(Long serviceId);
    Optional<Service> getServicesByServiceIdAndUser_IdAndIsActiveTrue(Long serviceId, Long providerId);
    List<Service> getAllServicesByUserIdAndIsActiveTrue(Long userId);
    List<Service> getAllServicesByIsActiveTrue();
}
