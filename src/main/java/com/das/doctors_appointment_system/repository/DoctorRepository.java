package com.das.doctors_appointment_system.repository;


import com.das.doctors_appointment_system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);
}