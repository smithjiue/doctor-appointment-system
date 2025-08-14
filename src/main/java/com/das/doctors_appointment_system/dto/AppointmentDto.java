package com.das.doctors_appointment_system.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDto {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentDateTime;
}