package com.example.Project2.controller;

import com.example.Project2.model.Property;
import com.example.Project2.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // --- CRUD Endpoints ---

    // Get all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Get only active properties
    @GetMapping("/active")
    public List<Property> getActiveProperties() {
        return propertyService.getActiveProperties();
    }

    // Get property by ID
    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    // Create new property
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    // Update property
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return propertyService.updateProperty(id, property);
    }

    // Soft delete property (mark inactive)
    @DeleteMapping("/soft/{id}")
    public void softDeleteProperty(@PathVariable Long id) {
        propertyService.softDeleteProperty(id);
    }

    // Hard delete property (remove from DB)
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }

    // --- Custom Query Endpoints ---

    // Get properties by user ID
    @GetMapping("/user/{userId}")
    public List<Property> getPropertiesByUserId(@PathVariable Long userId) {
        return propertyService.getPropertiesByUserId(userId);
    }

    // Get properties by name
    @GetMapping("/search/name/{name}")
    public List<Property> getPropertiesByName(@PathVariable String name) {
        return propertyService.getPropertiesByName(name);
    }

    // Get properties cheaper than a given price
    @GetMapping("/search/cheaper/{price}")
    public List<Property> getPropertiesCheaperThan(@PathVariable Double price) {
        return propertyService.getPropertiesCheaperThan(price);
    }

    // Get properties between two prices
    @GetMapping("/search/between")
    public List<Property> getPropertiesBetweenPrices(@RequestParam Double minPrice,
                                                     @RequestParam Double maxPrice) {
        return propertyService.getPropertiesBetweenPrices(minPrice, maxPrice);
    }
}