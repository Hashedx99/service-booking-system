package com.ga.project2.config;

import com.ga.project2.model.*;
import com.ga.project2.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final PropertyRepository propertyRepository;
    private final ServiceScheduleRepository serviceScheduleRepository;
    private final PropertyScheduleRepository propertyScheduleRepository;
    private final PropertyBookingRepository propertyBookingRepository;
    private final ServiceBookingRepository serviceBookingRepository;

    @Autowired
    public DataSeeder(
            UserRepository userRepository,
            ServiceRepository serviceRepository,
            PropertyRepository propertyRepository,
            ServiceScheduleRepository serviceScheduleRepository,
            PropertyScheduleRepository propertyScheduleRepository,
            PropertyBookingRepository propertyBookingRepository,
            ServiceBookingRepository serviceBookingRepository
    ) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.propertyRepository = propertyRepository;
        this.serviceScheduleRepository = serviceScheduleRepository;
        this.propertyScheduleRepository = propertyScheduleRepository;
        this.propertyBookingRepository = propertyBookingRepository;
        this.serviceBookingRepository = serviceBookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmailAddressAndIsActivatedTrue("admin@demo.com")) {
            return;
        }
        seedUsersAndData();
    }

    private void seedUsersAndData() {
        // Admin
        User admin = createUser("admin", "admin@demo.com", UserRoles.ADMIN);

        // Service Provider
        User serviceProvider = createUser("provider", "provider@demo.com", UserRoles.SERVICE_PROVIDER);

        // Owner
        User owner = createUser("owner", "owner@demo.com", UserRoles.OWNER);

        // Customer
        User customer = createUser("customer", "customer@demo.com", UserRoles.CUSTOMER);

        // Create Service for Service Provider
        Service service = new Service();
        service.setName("Haircut");
        service.setDescription("Basic haircut service");
        service.setPrice(20.0);
        service.setUser(serviceProvider);
        serviceRepository.save(service);

        // Create Schedule for Service
        ServiceSchedule schedule = new ServiceSchedule();
        schedule.setService(service);
        schedule.setDate(LocalDateTime.now().plusDays(1).toInstant(java.time.ZoneOffset.UTC));
        serviceScheduleRepository.save(schedule);

        // Create Property for Owner
        Property property = new Property();
        property.setName("Cozy Apartment");
        property.setDescription("A nice place to stay");
        property.setPrice(100.0);
        property.setActive(true);
        property.setUser(owner);
        propertyRepository.save(property);

        // Create Schedule for Service
        PropertySchedule propertySchedule = new PropertySchedule();
        propertySchedule.setProperty(property);
        propertySchedule.setDate(LocalDate.now().plusDays(1));
        propertyScheduleRepository.save(propertySchedule);

        // Create Property Booking for Customer
        PropertyBooking propertyBooking = new PropertyBooking();
        propertyBooking.setBookingDate(LocalDate.now().plusDays(2));
        propertyBooking.setUserId(customer.getId());
        propertyBooking.setProperty(property);
        propertyBooking.setActive(true);
        propertyBookingRepository.save(propertyBooking);

        // Create Service Booking for Customer
        ServiceBooking serviceBooking = new ServiceBooking();
        serviceBooking.setBookingDate(schedule.getDate());
        serviceBooking.setServiceId(service.getServiceId());
        serviceBooking.setProviderId(serviceProvider.getId());
        serviceBooking.setUserId(customer.getId());
        serviceBooking.setActive(true);
        serviceBookingRepository.save(serviceBooking);
    }

    private User createUser(String username, String email, UserRoles role) {
        if (userRepository.existsByEmailAddressAndIsActivatedTrue(email)) {
            return userRepository.findUserByEmailAddress(email);
        }
        User user = new User();
        user.setUserName(username);
        user.setEmailAddress(email);
        user.setPassword("$2a$10$QMuL5Y2xOGAQMVFhcUO5/.rQy41xdROjO9.1stF9.h7imI7NGW92K"); // "password"
        user.setActivated(true);
        user.setAccountVerified(true);
        user.setRole(role);
        UserProfile profile = new UserProfile();
        profile.setFirstName(username + "First");
        profile.setLastName(username + "Last");
        profile.setImage(new Image());
        user.setUserProfile(profile);
        return userRepository.save(user);
    }
}
