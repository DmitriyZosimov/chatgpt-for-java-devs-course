package com.example.service;

import com.example.model.Medication;

import java.util.List;

public interface MedicationService {
    List<Medication> getAllMedications();
    Medication getMedicationById(Long id);
    Medication createMedication(Medication medication);
    Medication updateMedication(Medication medication);
    void deleteMedication(Long id);
}

