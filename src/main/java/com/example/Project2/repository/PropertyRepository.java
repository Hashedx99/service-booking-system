package com.example.Project2.repository;


import com.example.Project2.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // ✅ Find all properties by user ID
    List<Property> findByUser_UserId(Long userId);

    // ✅ Find properties by name (case-insensitive)
    List<Property> findByNameIgnoreCase(String name);

    // ✅ Find properties cheaper than a given price
    List<Property> findByPriceLessThan(Double price);

    // ✅ Find properties with price between two values
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
}