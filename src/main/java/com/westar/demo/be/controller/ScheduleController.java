package com.westar.demo.be.controller;

import com.westar.demo.be.model.Schedule;
import com.westar.demo.be.model.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ScheduleController {

    private final Logger log = LoggerFactory.getLogger(ScheduleController.class);
    private ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/schedules")
    Collection<Schedule> schedules() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/schedule/{id}")
    ResponseEntity<?> getSchedule(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/schedule")
    ResponseEntity<Schedule> createSchedule(@Valid @RequestBody Schedule schedule) throws URISyntaxException {

        //schedule.getHotel().setId(Long.parseLong(schedule.getHotel().getName()));
        //schedule.getCar().setId(Long.parseLong(schedule.getCar().getName()));
        //schedule.getView().setId(Long.parseLong(schedule.getView().getName()));
        log.info("Request to create schedule: {}", schedule);

        Schedule result = scheduleRepository.save(schedule);
        return ResponseEntity.created(new URI("/api/schedule/" + result.getId()))
                .body(result);
    }

    @PutMapping("/schedule/{id}")
    ResponseEntity<Schedule> updateSchedule(@Valid @RequestBody Schedule schedule) {
        log.info("Request to update schedule: {}", schedule);
        Schedule result = scheduleRepository.save(schedule);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        log.info("Request to delete schedule: {}", id);
        scheduleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}