package com.example.Project2.service;

import com.example.Project2.controller.requests.service.CreateServiceRequest;
import com.example.Project2.controller.requests.service.UpdateServiceRequest;
import com.example.Project2.exception.InformationNotFoundException;
import com.example.Project2.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class ServiceService {
    private final UserService userService;
    private final ServiceRepository serviceRepository;

    // function to fetch the service by id
    private com.example.Project2.model.Service fetchServiceById(long serviceId) {
        // fetch the service or throw exception
        return serviceRepository.getServicesByServiceId(serviceId)
                .orElseThrow(() -> new InformationNotFoundException("Service with id: " + serviceId + " not found"));
    }

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

    // function to update a service
    public com.example.Project2.model.Service updateService(
            Long serviceId,
            UpdateServiceRequest request
    ) {
        // get the service by id
        var service = fetchServiceById(serviceId);

        // extract the request
        var name = request.getServiceName();
        var description = request.getServiceDescription();
        var price = request.getServicePrice();

        // update the provided fields
        if (StringUtils.hasText(name)) {
            service.setName(name);
        }
        if (StringUtils.hasText(description)) {
            service.setDescription(description);
        }
        if (StringUtils.hasText(price)) {
            service.setPrice(Double.parseDouble(price));
        }

        // update the changes
        serviceRepository.save(service);

        // return the updated entity
        return service;
    }

    // function to get a service by id
    public com.example.Project2.model.Service getServiceById(Long serviceId) {
        // get and return the service
        return fetchServiceById(serviceId);
    }
}
