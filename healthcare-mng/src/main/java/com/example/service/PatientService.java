package com.example.service;

import com.example.model.Medication;
import com.example.model.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Patient patient);
    void deletePatient(Long id);
    void addMedicationToPatient(Long patientId, Long medicationId);
}
