package com.das.doctors_appointment_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_appt_doctor_time", columnList = "doctor_id, appointmentDateTime", unique = true)
})
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;

    @NotNull
    private LocalDateTime appointmentDateTime;
}