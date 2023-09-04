package com.example.service;

import com.example.model.Medication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MedicationServiceImplTest {

    @Autowired
    private MedicationServiceImpl medicationService;

    @Test
    public void testGetMedicationById() {
        // Assuming there is a medication with ID 4 in the database
        Medication medication = medicationService.getMedicationById(4L);

        assertNotNull(medication);
        assertEquals(4L, medication.getId().longValue());
    }

    @Test
    public void testGetAllMedications() {
        // Assuming there are only 5 medications in the database
        List<Medication> medications = medicationService.getAllMedications();

        assertNotNull(medications);
        assertEquals(5, medications.size());
    }

    @Test
    @DirtiesContext
    public void testCreateMedication() {
        Medication medication = new Medication();
        medication.setName("Medication D");

        Medication createdMedication = medicationService.createMedication(medication);

        assertNotNull(createdMedication.getId());
        assertEquals("Medication D", createdMedication.getName());
    }

    @DirtiesContext
    @Test
    public void testUpdateMedication() {
        // Assuming there is a medication with ID 5 in the database
        Medication medication = medicationService.getMedicationById(5L);
        medication.setName("UpdatedMedicationName");

        Medication updatedMedication = medicationService.updateMedication(medication);

        assertNotNull(updatedMedication);
        assertEquals("UpdatedMedicationName", updatedMedication.getName());
    }

    @Test
    public void testUpdatePatient_ShouldReturnNull() {
        Medication medication = new Medication();
        medication.setId(999L);
        medication.setName("UpdatedMedicationName");

        Medication updatedMedication = medicationService.updateMedication(medication);

        assertNull(updatedMedication);
    }

    @DirtiesContext
    @Test
    public void testDeleteMedication() {
        // Assuming there is a medication with ID 5 in the database
        medicationService.deleteMedication(5L);

        assertNull(medicationService.getMedicationById(5L));
    }
}

