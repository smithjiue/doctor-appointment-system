package com.das.doctors_appointment_system.repository;

import com.das.doctors_appointment_system.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctor_IdAndAppointmentDateTime(Long doctorId, LocalDateTime time);
    Page<Appointment> findByDoctor_Id(Long doctorId, Pageable pageable);
    Page<Appointment> findByPatient_Id(Long patientId, Pageable pageable);
}