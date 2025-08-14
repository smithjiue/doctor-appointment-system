package com.das.doctors_appointment_system.controller;

import com.das.doctors_appointment_system.dto.PatientDto;
import com.das.doctors_appointment_system.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/patients") @RequiredArgsConstructor
public class PatientController {
    private final PatientService service;

    @PostMapping
    public PatientDto create(@Valid @RequestBody PatientDto dto) { return service.create(dto); }

    @GetMapping
    public Page<PatientDto> list(@RequestParam(defaultValue="0") int page,
                                 @RequestParam(defaultValue="20") int size,
                                 @RequestParam(defaultValue="id,asc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public PatientDto get(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public PatientDto update(@PathVariable Long id, @Valid @RequestBody PatientDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    private Sort.Order parseSort(String sort) {
        String[] s = sort.split(",");
        return new Sort.Order(Sort.Direction.fromString(s.length>1?s[1]:"asc"), s[0]);
    }
}