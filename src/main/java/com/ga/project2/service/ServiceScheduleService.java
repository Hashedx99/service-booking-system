package com.ga.project2.service;

import com.ga.project2.model.ServiceSchedule;
import com.ga.project2.repository.ServiceScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceScheduleService {
    private final ServiceService serviceService;
    private final ServiceScheduleRepository scheduleRepository;

    // function to create a new schedule
    public ServiceSchedule create(Long serviceId, Instant date) {
        // get the service from the db
        var service = serviceService.getServiceById(serviceId);

        // create schedule instance
        var schedule = new ServiceSchedule();

        // fill the required data
        schedule.setDate(date);
        schedule.setService(service);

        // return the updated entity
        return scheduleRepository.save(schedule);
    }

    // function to get a single schedule by id
    public ServiceSchedule getById(Long id) {
        // return the service or throw exception
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    // function to get all the schedules
    public List<ServiceSchedule> getAll() {
        // return list of schedules
        return scheduleRepository.findAll();
    }

    // function to get all schedules by service id
    public List<ServiceSchedule> getByServiceId(Long serviceId) {
        return scheduleRepository.findByServiceId(serviceId);
    }

    // function to update schedule by id
    public ServiceSchedule update(Long id, Instant date) {
        // get the schedule by id
        var schedule = getById(id);

        // update the date
        schedule.setDate(date);

        // return the updated entity
        return scheduleRepository.save(schedule);
    }

    // function to delete a schedule by id
    public void delete(Long id) {
        // get the schedule record
        var schedule = getById(id);

        // delete the schedule record
        scheduleRepository.delete(schedule);
    }
}
