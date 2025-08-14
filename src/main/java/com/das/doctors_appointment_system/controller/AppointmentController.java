package com.das.doctors_appointment_system.controller;


import com.das.doctors_appointment_system.dto.AppointmentDto;
import com.das.doctors_appointment_system.dto.CreateAppointmentRequest;
import com.das.doctors_appointment_system.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/appointments") @RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @PostMapping
    public AppointmentDto create(@Valid @RequestBody CreateAppointmentRequest req) {
        return service.create(req);
    }

    @GetMapping
    public Page<AppointmentDto> list(@RequestParam(required=false) Long doctorId,
                                     @RequestParam(required=false) Long patientId,
                                     @RequestParam(defaultValue="0") int page,
                                     @RequestParam(defaultValue="20") int size,
                                     @RequestParam(defaultValue="appointmentDateTime,asc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return service.findAll(doctorId, patientId, pageable);
    }

    @GetMapping("/{id}")
    public AppointmentDto get(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    private Sort.Order parseSort(String sort) {
        String[] s = sort.split(",");
        return new Sort.Order(Sort.Direction.fromString(s.length>1?s[1]:"asc"), s[0]);
    }
}