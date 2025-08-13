package com.das.doctors_appointment_system.repository;

import com.das.doctors_appointment_system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
