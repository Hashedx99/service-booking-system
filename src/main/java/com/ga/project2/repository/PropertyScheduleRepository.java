package com.ga.project2.repository;

import com.ga.project2.model.PropertySchedule;
import com.ga.project2.model.ServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyScheduleRepository extends JpaRepository<PropertySchedule, Long> {
    @Query("SELECT s FROM PropertySchedule s WHERE s.property.propertyId = :id")
    List<PropertySchedule> findByPropertyId(@Param("id") Long propertyId);
}
