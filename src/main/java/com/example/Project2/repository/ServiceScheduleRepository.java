package com.example.Project2.repository;

import com.example.Project2.model.ServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceScheduleRepository extends JpaRepository<ServiceSchedule, Long> {
    @Query("SELECT s FROM ServiceSchedule s WHERE s.service.serviceId = :id")
    List<ServiceSchedule> findByServiceId(@Param("id") Long serviceId);
}
