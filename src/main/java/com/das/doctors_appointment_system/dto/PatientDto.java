package com.das.doctors_appointment_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
public class PatientDto {
    private Long id;
    @NotBlank @Size(max=100) private String name;
    @Email @Size(max=150) private String email;
    @Size(max=20) private String phone;
}