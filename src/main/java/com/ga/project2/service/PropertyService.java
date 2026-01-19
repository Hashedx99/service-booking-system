package com.ga.project2.service;

import com.ga.project2.model.Property;
import com.ga.project2.model.User;
import com.ga.project2.model.request.CreatePropertyRequest;
import com.ga.project2.model.request.ImageModel;
import com.ga.project2.repository.ImageRepository;
import com.ga.project2.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ImageServiceImpl imageService;
    private final ImageRepository imageRepository;
    public PropertyService(PropertyRepository propertyRepository, ImageServiceImpl imageService, ImageRepository imageRepository) {
        this.propertyRepository = propertyRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    // --- CRUD Methods ---

    public Property saveProperty(CreatePropertyRequest model) {

        Property property =new Property();
        property.setActive(model.isActive());
        property.setName(model.getName());
        property.setPrice(model.getPrice());
        property.setDescription(model.getDescription());
        User user=new User();
        user.setId(model.getUserId());
        property.setUser(user);

        // upload the image
        var imageUrl = imageService.uploadImage(
                new ImageModel(
                        model.getName(),
                        model.getPropertyImage()
                ),
                "propertyImages"
        );

        // get the image by url
        var image = imageRepository.findByUrl(imageUrl);

        property.setImage(image);

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