package com.ga.project2.controller;

import com.ga.project2.model.ServiceSchedule;
import com.ga.project2.service.ServiceScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ServiceSchedule> create(
            @RequestParam Long serviceId,
            @RequestParam Instant date
    ) {
        // create the schedule
        var schedule = scheduleService.create(serviceId, date);

        // return the created schedule in the body
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ServiceSchedule> getById(@PathVariable Long id) {
        // return the schedule
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ServiceSchedule>> getAll() {
        // return the list of schedules
        return ResponseEntity.ok(scheduleService.getAll());
    }

    // READ BY SERVICE
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceSchedule>> getByService(
            @PathVariable Long serviceId
    ) {
        // return the updated entity as the body
        return ResponseEntity.ok(scheduleService.getByServiceId(serviceId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ServiceSchedule> update(
            @PathVariable Long id,
            @RequestParam Instant date
    ) {
        return ResponseEntity.ok(scheduleService.update(id, date));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // delete the schedule
        scheduleService.delete(id);

        // return 204 response
        return ResponseEntity.noContent().build();
    }
}
