package com.ga.project2.controller;

import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.Service;
import com.ga.project2.model.request.CreateServiceRequest;
import com.ga.project2.model.request.UpdateServiceRequest;
import com.ga.project2.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    // CREATE
    @PostMapping
    public Service createService(@ModelAttribute CreateServiceRequest request) throws UserNotAuthorizedException {
        return serviceService.createService(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Service updateService(@RequestBody UpdateServiceRequest request,
                                 @PathVariable(name = "id") long serviceId) throws UserNotAuthorizedException {

        return serviceService.updateService(serviceId, request);

    }

    // READ ONE
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable(name = "id") long serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    // READ ALL
    @GetMapping("/my-services")
    public List<Service> getAllMyServices() {
        // get all the services
        return serviceService.getAllMyServices();
    }

    @GetMapping
    public List<Service> getAllActiveServices() {
        // get all the active services
        return serviceService.getAllServices();
    }

    // READ BY USER (NESTED RESOURCE)
    @GetMapping("/users/{userId}")
    public List<Service> getServicesByUserId(@PathVariable Long userId) {
        // get the user services
        return serviceService.getServicesByUserId(userId);
    }

    // HARD DELETE
    @DeleteMapping("/{id}")
    public Service deleteService(@PathVariable(name = "id") long serviceId) throws UserNotAuthorizedException {
        // try to delete a service
        return serviceService.deleteService(serviceId);

    }

    // SOFT DELETE (STATE CHANGE)
    @PatchMapping("/{id}")
    public Service deactivateService(@PathVariable(name = "id") Long serviceId) throws UserNotAuthorizedException {
        // delete the service
        return serviceService.deactivateService(serviceId);
    }

}
