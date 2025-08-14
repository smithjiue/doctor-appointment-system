package com.das.doctors_appointment_system.service;

import com.das.doctors_appointment_system.dto.CreateAppointmentRequest;
import com.das.doctors_appointment_system.dto.DoctorDto;
import com.das.doctors_appointment_system.dto.PatientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {
    @Autowired DoctorService doctorService;
    @Autowired PatientService patientService;
    @Autowired AppointmentService appointmentService;

    @Test
    void preventsDoubleBooking() {
        var d = doctorService.create(DoctorDto.builder().name("Dr A").specialization("Cardio").build());
        var p = patientService.create(PatientDto.builder().name("Pat A").build());
        var when = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);

        appointmentService.create(new CreateAppointmentRequest(d.getId(), p.getId(), when));
        var ex = assertThrows(IllegalArgumentException.class, () ->
                appointmentService.create(new CreateAppointmentRequest(d.getId(), p.getId(), when))
        );
        assertTrue(ex.getMessage().contains("already"));
    }
}