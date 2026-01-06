package com.example.Project2.controller;

import com.example.Project2.controller.requests.service.CreateServiceRequest;
import com.example.Project2.controller.requests.service.UpdateServiceRequest;
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

    // create service api endpoint
    @PostMapping("/create")
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

    // update service api endpoint
    @PutMapping("/{id}/update")
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

    // get single service api endpoint
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

    // get all services for a user api endpoint
    @GetMapping("/")
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

    // delete a single service
    @DeleteMapping("/{id}/delete")
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
}
