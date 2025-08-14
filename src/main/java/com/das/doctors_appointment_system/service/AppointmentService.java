package com.das.doctors_appointment_system.service;

import com.das.doctors_appointment_system.dto.AppointmentDto;
import com.das.doctors_appointment_system.dto.CreateAppointmentRequest;
import com.das.doctors_appointment_system.exception.NotFoundException;
import com.das.doctors_appointment_system.model.Appointment;
import com.das.doctors_appointment_system.model.Doctor;
import com.das.doctors_appointment_system.model.Patient;
import com.das.doctors_appointment_system.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repo;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Transactional
    public AppointmentDto create(CreateAppointmentRequest req) {
        Doctor doc = doctorService.getOrThrow(req.getDoctorId());
        Patient pat = patientService.getOrThrow(req.getPatientId());

        if (repo.existsByDoctor_IdAndAppointmentDateTime(doc.getId(), req.getAppointmentDateTime())) {
            throw new IllegalArgumentException("Doctor already has an appointment at this time");
        }

        Appointment appt = Appointment.builder()
                .doctor(doc).patient(pat).appointmentDateTime(req.getAppointmentDateTime()).build();

        return toDto(repo.save(appt));
    }

    public Page<AppointmentDto> findAll(Long doctorId, Long patientId, Pageable pageable) {
        if (doctorId != null) return repo.findByDoctor_Id(doctorId, pageable).map(this::toDto);
        if (patientId != null) return repo.findByPatient_Id(patientId, pageable).map(this::toDto);
        return repo.findAll(pageable).map(this::toDto);
    }

    public AppointmentDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Appointment not found: " + id);
        repo.deleteById(id);
    }

    public Appointment getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found: " + id));
    }

    private AppointmentDto toDto(Appointment a) {
        return AppointmentDto.builder()
                .id(a.getId())
                .doctorId(a.getDoctor().getId())
                .patientId(a.getPatient().getId())
                .appointmentDateTime(a.getAppointmentDateTime())
                .build();
    }
}