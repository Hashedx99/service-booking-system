package com.ga.project2.controller;

import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.Property;

import com.ga.project2.model.request.CreatePropertyRequest;
import com.ga.project2.service.PropertyService;
import com.ga.project2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

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
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    // Create new property
    @PostMapping
    public Property createProperty(@ModelAttribute CreatePropertyRequest model) throws UserNotAuthorizedException {
        return propertyService.saveProperty(model);
    }

    // Update property
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) throws UserNotAuthorizedException {
        return propertyService.updateProperty(id, property);
    }

    // Soft delete property (mark inactive)
    @DeleteMapping("/soft/{id}")
    public void softDeleteProperty(@PathVariable Long id) throws UserNotAuthorizedException {
        propertyService.softDeleteProperty(id);
    }

    // Hard delete property (remove from DB)
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) throws UserNotAuthorizedException {
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