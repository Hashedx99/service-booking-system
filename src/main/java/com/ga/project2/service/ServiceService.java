package com.ga.project2.service;

import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.UserRoles;
import com.ga.project2.model.Image;
import com.ga.project2.model.request.CreateServiceRequest;
import com.ga.project2.model.request.ImageModel;
import com.ga.project2.model.request.UpdateServiceRequest;
import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.repository.ImageRepository;
import com.ga.project2.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {
    private final UserService userService;
    private final ServiceRepository serviceRepository;
    private final ImageServiceImpl imageService;
    private final ImageRepository imageRepository;

    // function to fetch the service by id
    private com.ga.project2.model.Service fetchServiceById(long serviceId) throws UserNotAuthorizedException {
        // fetch the service or throw exception
        if (UserRoles.SERVICE_PROVIDER == userService.getUser().getRole()) {
            return serviceRepository.getServicesByServiceIdAndUser_IdAndIsActiveTrue(serviceId,
                            userService.getUser().getId())
                    .orElseThrow(() -> new InformationNotFoundException("Service with id: " + serviceId + " not " +
                            "found"));
        } else if (UserRoles.ADMIN == userService.getUser().getRole()) {
            return serviceRepository.getServicesByServiceIdAndIsActiveTrue(serviceId)
                    .orElseThrow(() -> new InformationNotFoundException("Service with id: " + serviceId + " not " +
                            "found"));
        } else {
            throw new UserNotAuthorizedException("User not authorized to access this resource");
        }
    }

    // function to create a service
    public com.ga.project2.model.Service createService(CreateServiceRequest request) {
        // get the user from the context holder
        var user = userService.getUser();

        // create service instance
        var service = new com.ga.project2.model.Service();

        // upload the image
        var imageUrl = imageService.uploadImage(
                new ImageModel(
                        request.getServiceName(),
                        request.getServiceImage()
                ),
                "serviceImages"
        );

        // get the image by url
        var image = imageRepository.findByUrl(imageUrl);

        // set the fields
        service.setName(request.getServiceName());
        service.setDescription(request.getServiceDescription());
        service.setPrice(request.getServicePrice());
        service.setUser(user);
        service.setImage(image);

        // save the record
        serviceRepository.save(service);

        // return the created entity
        return service;
    }

    // function to update a service
    public com.ga.project2.model.Service updateService(
            Long serviceId,
            UpdateServiceRequest request
    ) throws UserNotAuthorizedException {
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
    public com.ga.project2.model.Service getServiceById(Long serviceId) throws UserNotAuthorizedException {
        // get and return the service
        return fetchServiceById(serviceId);
    }

    // function to get all the services for a user
    public List<com.ga.project2.model.Service> getAllMyServices() {
        // get the authenticated user
        var user = userService.getUser();

        // get and return all the services that belong to the user
        return serviceRepository.getAllServicesByUserIdAndIsActiveTrue(user.getId());
    }

    public List<com.ga.project2.model.Service> getAllServices() {
        // get and return all the services
        return serviceRepository.getAllServicesByIsActiveTrue();
    }

    // function to get all the services for a user by user id
    public List<com.ga.project2.model.Service> getServicesByUserId(Long userId) {
        // get and return all the services that belong to the user
        return serviceRepository.getAllServicesByUserIdAndIsActiveTrue(userId);
    }

    // function to delete a service by id
    public com.ga.project2.model.Service deleteService(Long serviceId) throws UserNotAuthorizedException {
        if (UserRoles.ADMIN != userService.getUser().getRole()) {
            throw new UserNotAuthorizedException("Only admins can delete services");
        }

        // get the service
        var service = fetchServiceById(serviceId);

        // delete the service
        serviceRepository.delete(service);

        // return the deleted service
        return service;
    }

    // function to deactivate a service by id
    public com.ga.project2.model.Service deactivateService(Long serviceId) throws UserNotAuthorizedException {
        // get the service
        var service = fetchServiceById(serviceId);

        // make it not active
        service.setIsActive(false);

        // save the changes
        serviceRepository.save(service);

        // return the deleted service
        return service;
    }
}
