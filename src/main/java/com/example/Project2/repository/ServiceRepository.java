package com.example.Project2.repository;

import com.example.Project2.model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    Optional<Service> getServicesByServiceIdAndIsActiveTrue(Long serviceId);
    List<Service> getAllServicesByUserIdAndIsActiveTrue(Long userId);
}
