package com.example.mindmeetapp.controller;

import com.example.mindmeetapp.model.AppointmentModel;
import com.example.mindmeetapp.service.AppointmentService;
import com.example.mindmeetapp.util.AppointmentException;
import com.example.mindmeetapp.util.AppointmentNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)

public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;


    @Test
    void getAppointmentById_WhenValidId_ReturnsAppointment() throws Exception {

        int appointmentId = 1;
        AppointmentModel appointment = new AppointmentModel();
        appointment.setId(1);

        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(appointment);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/{id}", appointmentId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(appointmentId));

        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
    }

    @Test
    void getAppointmentById_WhenInvalidId_ReturnsNotFoundStatus() throws Exception {
        int appointmentId = 1;

        when(appointmentService.getAppointmentById(appointmentId)).thenThrow(new AppointmentNotFoundException("Appointment not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/{id}", appointmentId))
                .andExpect(status().isNotFound());

        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
    }




}


