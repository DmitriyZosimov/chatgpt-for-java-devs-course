package com.example.service;

import com.example.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class PatientServiceImplTest {

    @Autowired
    private PatientServiceImpl patientService;

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");

        Patient createdPatient = patientService.createPatient(patient);

        assertNotNull(createdPatient.getId());
        assertEquals("John", createdPatient.getFirstName());
        assertEquals("Doe", createdPatient.getLastName());
    }

    @Test
    public void testGetPatientById() {
        // Assuming there is a patient with ID 1 in the database
        Patient patient = patientService.getPatientById(1L);

        assertNotNull(patient);
        assertEquals(1L, patient.getId().longValue());
    }

    @Test
    public void testGetAllPatients() {
        // Assuming there are only 3 patients in the database
        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(3, patients.size());
    }

    @Test
    public void testUpdatePatient() {
        // Assuming there is a patient with ID 2 in the database
        Patient patient = patientService.getPatientById(2L);
        patient.setFirstName("UpdatedFirstName");
        patient.setLastName("UpdatedLastName");

        Patient updatedPatient = patientService.updatePatient(patient);

        assertNotNull(updatedPatient);
        assertEquals("UpdatedFirstName", updatedPatient.getFirstName());
        assertEquals("UpdatedLastName", updatedPatient.getLastName());
    }

    @Test
    public void testUpdatePatient_ShouldReturnNull() {
        // Assuming there is a patient with ID 2 in the database
        Patient patient = new Patient();
        patient.setId(999L);
        patient.setFirstName("UpdatedFirstName");
        patient.setLastName("UpdatedLastName");

        Patient updatedPatient = patientService.updatePatient(patient);

        assertNull(updatedPatient);
    }

    @Test
    public void testDeletePatient() {
        // Assuming there is a patient with ID 3 in the database
        patientService.deletePatient(3L);

        assertNull(patientService.getPatientById(3L));
    }

    @Test
    public void testAddMedicationToPatient() {
        // Assuming there is a patient with ID 3 and medication with ID 5 in the database
        Long patientId = 3L;
        Long medicationId = 5L;

        patientService.addMedicationToPatient(patientId, medicationId);

        Patient patient = patientService.getPatientById(patientId);
        assertNotNull(patient);
        assertEquals(1, patient.getPrescribedMedications().size());
        assertEquals(medicationId, patient.getPrescribedMedications().get(0).getId());
    }

    @Test
    public void testNotAddMedicationToPatientWhenNoSuchPatient() {
        Long patientId = 99L;
        Long medicationId = 5L;

        patientService.addMedicationToPatient(patientId, medicationId);

        Patient patient = patientService.getPatientById(patientId);
        assertNull(patient);
    }
}


