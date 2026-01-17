package com.example.Project2.controller;

import com.example.Project2.model.request.CreateServiceRequest;
import com.example.Project2.model.request.UpdateServiceRequest;
import com.example.Project2.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createService(
            @RequestBody CreateServiceRequest request
    ) {
        // try to create the service
        try {
            // create the service
            var service = serviceService.createService(request);

            // return the created service as the body
            return ResponseEntity.status(HttpStatus.CREATED).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(
            @RequestBody UpdateServiceRequest request,
            @PathVariable(name = "id") long serviceId
    ) {
        // try to update the service
        try {
            // update the service
            var service = serviceService.updateService(serviceId, request);

            // return the updated service as the body
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(
            @PathVariable(name = "id") long serviceId
    ) {
        // try to get the service
        try {
            // get the service
            var service = serviceService.getServiceById(serviceId);

            // return the fetched service as the body
            return ResponseEntity.status(HttpStatus.OK).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllServices() {
        // try to get all the services for the authenticated user
        try {
            // get all the services
            var services = serviceService.getAllServices();

            // return the services as the body
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // READ BY USER (NESTED RESOURCE)
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getServicesByUserId(
            @PathVariable Long userId
    ) {
        try {
            // get the user services
            var services = serviceService.getServicesByUserId(userId);

            // return the user services
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // HARD DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(
            @PathVariable(name = "id") long serviceId
    ) {
        // try to delete a service
        try {
            // delete the service
            var service = serviceService.deleteService(serviceId);

            // return the deleted service as the body
            return ResponseEntity.status(HttpStatus.OK).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // SOFT DELETE (STATE CHANGE)
    @PatchMapping("/{id}")
    public ResponseEntity<?> deactivateService(
            @PathVariable(name = "id") Long serviceId
    ) {
        // try to delete a service
        try {
            // delete the service
            var service = serviceService.deactivateService(serviceId);

            // return the deleted service as the body
            return ResponseEntity.status(HttpStatus.OK).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
