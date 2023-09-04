package com.example.controller;

import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    public void testGetAllAppointments() throws Exception {
        List<Appointment> appointments = Arrays.asList(
                new Appointment(1L, LocalDate.of(2023, 8, 25), new Patient(1L, "John", "Doe", null)),
                new Appointment(2L, LocalDate.of(2023, 8, 26), new Patient(2L, "Jane", "Smith", null))
        );

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].date", is("2023-08-25")))
                .andExpect(jsonPath("$[0].patient.id", is(1)))
                .andExpect(jsonPath("$[0].patient.firstName", is("John")))
                .andExpect(jsonPath("$[0].patient.lastName", is("Doe")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].date", is("2023-08-26")))
                .andExpect(jsonPath("$[1].patient.id", is(2)))
                .andExpect(jsonPath("$[1].patient.firstName", is("Jane")))
                .andExpect(jsonPath("$[1].patient.lastName", is("Smith")));
    }

    @Test
    public void testGetAppointmentById() throws Exception {
        Appointment appointment = new Appointment(1L, LocalDate.of(2023, 8, 25), new Patient(1L, "John", "Doe", new ArrayList<>()));

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.date", is("2023-08-25")))
                .andExpect(jsonPath("$.patient.id", is(1)))
                .andExpect(jsonPath("$.patient.firstName", is("John")))
                .andExpect(jsonPath("$.patient.lastName", is("Doe")));
    }

    @Test
    public void testGetAppointmentById_NotFound() throws Exception {
        when(appointmentService.getAppointmentById(1L)).thenReturn(null);

        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAppointment() throws Exception {
        Appointment appointment = new Appointment(1L, LocalDate.of(2023, 8, 25), new Patient(1L, "John", "Doe", new ArrayList<>()));

        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-08-25\",\"patient\":{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.date", is("2023-08-25")))
                .andExpect(jsonPath("$.patient.id", is(1)))
                .andExpect(jsonPath("$.patient.firstName", is("John")))
                .andExpect(jsonPath("$.patient.lastName", is("Doe")));
    }

    @Test
    public void testUpdateAppointment() throws Exception {
        Appointment updatedAppointment = new Appointment(1L, LocalDate.of(2023, 8, 26), new Patient(1L, "UpdatedFirstName", "UpdatedLastName", new ArrayList<>()));

        when(appointmentService.updateAppointment(any(Appointment.class))).thenReturn(updatedAppointment);

        mockMvc.perform(put("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-08-26\",\"patient\":{\"id\":1,\"firstName\":\"UpdatedFirstName\",\"lastName\":\"UpdatedLastName\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.date", is("2023-08-26")))
                .andExpect(jsonPath("$.patient.id", is(1)))
                .andExpect(jsonPath("$.patient.firstName", is("UpdatedFirstName")))
                .andExpect(jsonPath("$.patient.lastName", is("UpdatedLastName")));
    }

    @Test
    public void testUpdateAppointment_NotFound() throws Exception {
        when(appointmentService.updateAppointment(any(Appointment.class))).thenReturn(null);

        mockMvc.perform(put("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-08-26\",\"patient\":{\"id\":1,\"firstName\":\"UpdatedFirstName\",\"lastName\":\"UpdatedLastName\"}}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/appointments/1"))
                .andExpect(status().isNoContent());
    }
}

