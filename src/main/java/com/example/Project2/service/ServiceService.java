package com.example.Project2.service;

import com.example.Project2.controller.requests.service.CreateServiceRequest;
import com.example.Project2.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceService {
    private final UserService userService;
    private final ServiceRepository serviceRepository;

    // function to create a service
    public com.example.Project2.model.Service createService(CreateServiceRequest request) {
        // get the user from the context holder
        var user = userService.getUser();

        // create service instance
        var service = new com.example.Project2.model.Service();

        // pass the fields
        service.setName(request.getServiceName());
        service.setDescription(request.getServiceDescription());
        service.setPrice(request.getServicePrice());
        service.setProviderId(user.getId());

        // save the record
        serviceRepository.save(service);

        // return the created entity
        return service;
    }
}
