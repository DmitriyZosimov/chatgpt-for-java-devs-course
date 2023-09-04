package com.example.service;

import com.example.model.Medication;
import com.example.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    @Transactional
    public Medication updateMedication(Medication medication) {
        Medication existingMedication = getMedicationById(medication.getId());
        if (existingMedication != null) {
            existingMedication.setName(medication.getName());
            return medicationRepository.save(existingMedication);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}

