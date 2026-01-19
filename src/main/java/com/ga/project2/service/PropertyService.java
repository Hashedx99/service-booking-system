package com.ga.project2.service;

import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.Property;
import com.ga.project2.model.UserRoles;
import com.ga.project2.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final UserService userService;

    // --- CRUD Methods ---

    public Property saveProperty(Property property) {
        property.setUser(userService.getUser());
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    //Update property details
    public Property updateProperty(Long id, Property updatedProperty) throws UserNotAuthorizedException {
        if (UserRoles.OWNER == userService.getUser().getRole()) {
            return propertyRepository.findByPropertyIdAndUserId(id, userService.getUser().getId())
                    .map(existing -> {
                        existing.setName(updatedProperty.getName());
                        existing.setDescription(updatedProperty.getDescription());
                        existing.setPrice(updatedProperty.getPrice());
                        existing.setScheduleId(updatedProperty.getScheduleId());
                        existing.setActive(updatedProperty.isActive());
                        existing.setUser(updatedProperty.getUser());
                        return propertyRepository.save(existing);
                    })
                    .orElseThrow(() -> new InformationNotFoundException("Property not found with id " + id));
        } else if (UserRoles.ADMIN == userService.getUser().getRole()) {
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
                    .orElseThrow(() -> new InformationNotFoundException("Property not found with id " + id));
        } else {
            throw new UserNotAuthorizedException("Unauthorized to update property");
        }
    }

    //Soft delete (mark as inactive instead of removing)
    public void softDeleteProperty(Long id) throws UserNotAuthorizedException {
        if (UserRoles.OWNER == userService.getUser().getRole()) {
            propertyRepository.findByPropertyIdAndUserId(id, userService.getUser().getId())
                    .map(property -> {
                        property.setActive(false);
                        return propertyRepository.save(property);
                    }).orElseThrow(
                            () -> new InformationNotFoundException("Property not found with id " + id)
                    );
        } else if (UserRoles.ADMIN == userService.getUser().getRole()) {
            propertyRepository.findById(id)
                    .map(property -> {
                        property.setActive(false);
                        return propertyRepository.save(property);
                    }).orElseThrow(
                            () -> new InformationNotFoundException("Property not found with id " + id)
                    );
        } else {
            throw new UserNotAuthorizedException("Unauthorized to delete property");
        }
    }

    // Hard delete if needed
    public void deleteProperty(Long id) throws UserNotAuthorizedException {
        if (UserRoles.ADMIN == userService.getUser().getRole()) {
            propertyRepository.deleteById(id);
        } else {
            throw new UserNotAuthorizedException("Only admins can hard delete properties");
        }
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