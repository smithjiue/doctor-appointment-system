package com.das.doctors_appointment_system.controller;

import com.das.doctors_appointment_system.dto.DoctorDto;
import com.das.doctors_appointment_system.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/doctors") @RequiredArgsConstructor
public class DoctorController {
    private final DoctorService service;

    @PostMapping
    public DoctorDto create(@Valid @RequestBody DoctorDto dto) { return service.create(dto); }

    @GetMapping
    public Page<DoctorDto> list(@RequestParam(defaultValue="0") int page,
                                @RequestParam(defaultValue="20") int size,
                                @RequestParam(defaultValue="id,asc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public DoctorDto get(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public DoctorDto update(@PathVariable Long id, @Valid @RequestBody DoctorDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    private Sort.Order parseSort(String sort) {
        String[] s = sort.split(",");
        return new Sort.Order(Sort.Direction.fromString(s.length>1?s[1]:"asc"), s[0]);
    }
}