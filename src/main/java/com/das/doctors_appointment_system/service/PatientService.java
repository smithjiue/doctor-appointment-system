package com.das.doctors_appointment_system.service;

import com.das.doctors_appointment_system.dto.PatientDto;
import com.das.doctors_appointment_system.exception.NotFoundException;
import com.das.doctors_appointment_system.model.Patient;
import com.das.doctors_appointment_system.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    public PatientDto create(PatientDto dto) {
        Patient p = toEntity(dto); p.setId(null);
        return toDto(repo.save(p));
    }

    public Page<PatientDto> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toDto);
    }

    public PatientDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    public PatientDto update(Long id, PatientDto dto) {
        Patient p = getOrThrow(id);
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        return toDto(repo.save(p));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Patient not found: " + id);
        repo.deleteById(id);
    }

    public Patient getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Patient not found: " + id));
    }

    private PatientDto toDto(Patient p) {
        return PatientDto.builder()
                .id(p.getId()).name(p.getName()).email(p.getEmail()).phone(p.getPhone()).build();
    }
    private Patient toEntity(PatientDto dto) {
        return Patient.builder()
                .id(dto.getId()).name(dto.getName()).email(dto.getEmail()).phone(dto.getPhone()).build();
    }
}