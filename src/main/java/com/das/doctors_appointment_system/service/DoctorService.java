package com.das.doctors_appointment_system.service;

import com.das.doctors_appointment_system.dto.DoctorDto;
import com.das.doctors_appointment_system.exception.NotFoundException;
import com.das.doctors_appointment_system.model.Doctor;
import com.das.doctors_appointment_system.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository repo;

    public DoctorDto create(DoctorDto dto) {
        Doctor d = toEntity(dto);
        d.setId(null);
        return toDto(repo.save(d));
    }

    public Page<DoctorDto> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toDto);
    }

    public DoctorDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    public DoctorDto update(Long id, DoctorDto dto) {
        Doctor existing = getOrThrow(id);
        existing.setName(dto.getName());
        existing.setSpecialization(dto.getSpecialization());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        return toDto(repo.save(existing));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Doctor not found: " + id);
        repo.deleteById(id);
    }

    public Doctor getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Doctor not found: " + id));
    }

    private DoctorDto toDto(Doctor d) {
        return DoctorDto.builder()
                .id(d.getId()).name(d.getName()).specialization(d.getSpecialization())
                .email(d.getEmail()).phone(d.getPhone()).build();
    }
    private Doctor toEntity(DoctorDto dto) {
        return Doctor.builder()
                .id(dto.getId()).name(dto.getName()).specialization(dto.getSpecialization())
                .email(dto.getEmail()).phone(dto.getPhone()).build();
    }
}