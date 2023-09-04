package com.example.controller;

import com.example.model.Medication;
import com.example.service.MedicationService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(MedicationController.class)
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    @Test
    public void testGetAllMedications() throws Exception {
        List<Medication> medications = Arrays.asList(
                new Medication(1L, "Medication A"),
                new Medication(2L, "Medication B")
        );

        when(medicationService.getAllMedications()).thenReturn(medications);

        mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Medication A")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Medication B")));
    }

    @Test
    public void testGetMedicationById() throws Exception {
        Medication medication = new Medication(1L, "Medication A");

        when(medicationService.getMedicationById(1L)).thenReturn(medication);

        mockMvc.perform(get("/medications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Medication A")));
    }

    @Test
    public void testGetMedicationById_NotFound() throws Exception {
        when(medicationService.getMedicationById(1L)).thenReturn(null);

        mockMvc.perform(get("/medications/1")).andExpect(status().isNotFound());
    }

    @Test
    public void testCreateMedication() throws Exception {
        Medication medication = new Medication(1L, "Medication D");

        when(medicationService.createMedication(any(Medication.class))).thenReturn(medication);

        mockMvc.perform(post("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Medication D\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Medication D")));
    }

    @Test
    public void testUpdateMedication() throws Exception {
        Medication updatedMedication = new Medication(1L, "UpdatedMedicationName");

        when(medicationService.updateMedication(any(Medication.class))).thenReturn(updatedMedication);

        mockMvc.perform(put("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedMedicationName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("UpdatedMedicationName")));
    }

    @Test
    public void testUpdateMedication_NotFound() throws Exception {
        when(medicationService.updateMedication(any(Medication.class))).thenReturn(null);

        mockMvc.perform(put("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedMedicationName\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteMedication() throws Exception {
        mockMvc.perform(delete("/medications/1")).andExpect(status().isNoContent());
    }
}

