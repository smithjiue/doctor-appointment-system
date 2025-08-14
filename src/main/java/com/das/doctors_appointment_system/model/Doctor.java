package com.das.doctors_appointment_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max=100)
    private String name;

    @NotBlank @Size(max=100)
    private String specialization;

    @Email
    @Size(max=150)
    @Column(unique = true)
    private String email;

    @Size(max=20)
    private String phone;
}