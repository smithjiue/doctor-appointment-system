package com.das.doctors_appointment_system.controller;

import com.das.doctors_appointment_system.dto.DoctorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest @AutoConfigureMockMvc
class DoctorControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test
    void createDoctor_201() throws Exception {
        DoctorDto dto = DoctorDto.builder()
                .name("Dr. House").specialization("Diagnostics").email("house@example.com").phone("9999999999").build();

        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}