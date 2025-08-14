package com.das.doctors_appointment_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateAppointmentRequest {
    @NotNull private Long doctorId;
    @NotNull private Long patientId;
    @NotNull private LocalDateTime appointmentDateTime;

}