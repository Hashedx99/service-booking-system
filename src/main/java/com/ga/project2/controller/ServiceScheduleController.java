package com.ga.project2.controller;

import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.ServiceSchedule;
import com.ga.project2.service.ServiceScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/service-schedules")
public class ServiceScheduleController {
    private final ServiceScheduleService scheduleService;

    // CREATE
    @PostMapping
    public ServiceSchedule create(
            @RequestParam Long serviceId,
            @RequestParam Instant date
    ) throws UserNotAuthorizedException {
        return scheduleService.create(serviceId, date);

    }

    // READ ONE
    @GetMapping("/{id}")
    public ServiceSchedule getById(@PathVariable Long id) {
        // return the schedule
        return scheduleService.getById(id);
    }

    // READ ALL
    @GetMapping
    public List<ServiceSchedule> getAll() {
        // return the list of schedules
        return scheduleService.getAll();
    }

    // READ BY SERVICE
    @GetMapping("/service/{serviceId}")
    public List<ServiceSchedule> getByService(
            @PathVariable Long serviceId
    ) {
        // return the updated entity as the body
        return scheduleService.getByServiceId(serviceId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ServiceSchedule update(
            @PathVariable Long id,
            @RequestParam Instant date
    ) {
        return scheduleService.update(id, date);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }
}
