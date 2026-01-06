package com.example.Project2.controller;

import com.example.Project2.controller.requests.service.CreateServiceRequest;
import com.example.Project2.repository.ServiceRepository;
import com.example.Project2.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            throw new RuntimeException(e);
        }
    }

    // todo: update service api endpoint

    // todo: get single service api endpoint

    // todo:  get all services for a user api endpoint

    // todo: delete a single service
}
