package com.example.controller;

import com.example.model.Patient;
import com.example.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void testGetAllPatients() throws Exception {
        List<Patient> patients = Arrays.asList(
                Patient.builder().id(1L).firstName("John").lastName("Doe").build(),
                Patient.builder().id(2L).firstName("Jane").lastName("Smith").build()
        );

        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Jane")))
                .andExpect(jsonPath("$[1].lastName", is("Smith")));
    }

    @Test
    public void testGetPatientById() throws Exception {
        Patient patient = Patient.builder().id(1L).firstName("John").lastName("Doe").build();

        when(patientService.getPatientById(1L)).thenReturn(patient);

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    @Test
    public void testGetPatientById_NotFound() throws Exception {
        when(patientService.getPatientById(1L)).thenReturn(null);

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = Patient.builder().id(1L).firstName("John").lastName("Doe").build();

        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient updatedPatient = Patient.builder().id(1L).firstName("UpdatedFirstName").lastName("UpdatedLastName").build();

        when(patientService.updatePatient(any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"UpdatedFirstName\",\"lastName\":\"UpdatedLastName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("UpdatedFirstName")))
                .andExpect(jsonPath("$.lastName", is("UpdatedLastName")));
    }

    @Test
    public void testUpdatePatient_NotFound() throws Exception {
        when(patientService.updatePatient(any(Patient.class))).thenReturn(null);

        mockMvc.perform(put("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"UpdatedFirstName\",\"lastName\":\"UpdatedLastName\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePatient() throws Exception {
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddMedicationToPatient() throws Exception {
        Patient patient = Patient.builder().id(1L).firstName("John").lastName("Doe").build();

        doNothing().when(patientService).addMedicationToPatient(anyLong(), anyLong());

        mockMvc.perform(post("/patients/1/medications/2")).andExpect(status().isOk());
    }
}

