package com.ga.project2.service;

import com.ga.project2.model.Property;
import com.ga.project2.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // --- CRUD Methods ---

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    //Update property details
    public Property updateProperty(Long id, Property updatedProperty) {
        return propertyRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedProperty.getName());
                    existing.setDescription(updatedProperty.getDescription());
                    existing.setPrice(updatedProperty.getPrice());
                    existing.setScheduleId(updatedProperty.getScheduleId());
                    existing.setActive(updatedProperty.isActive());
                    existing.setUser(updatedProperty.getUser());
                    return propertyRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Property not found with id " + id));
    }

    //Soft delete (mark as inactive instead of removing)
    public void softDeleteProperty(Long id) {
        propertyRepository.findById(id)
                .ifPresent(property -> {
                    property.setActive(false);
                    propertyRepository.save(property);
                });
    }

    // Hard delete if needed
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    // --- Custom Queries ---
    public List<Property> getPropertiesByUserId(Long Id) {
        return propertyRepository.findByUserId(Id);
    }

    public List<Property> getPropertiesByName(String name) {
        return propertyRepository.findByNameIgnoreCase(name);
    }

    public List<Property> getPropertiesCheaperThan(Double price) {
        return propertyRepository.findByPriceLessThan(price);
    }

    public List<Property> getPropertiesBetweenPrices(Double minPrice, Double maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Only return active properties
    public List<Property> getActiveProperties() {
        return propertyRepository.findAll()
                .stream()
                .filter(Property::isActive)
                .toList();
    }
}