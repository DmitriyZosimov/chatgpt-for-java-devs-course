package com.example.service;

import com.example.model.Medication;
import com.example.model.Patient;
import com.example.repository.MedicationRepository;
import com.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, MedicationRepository medicationRepository) {
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Patient updatePatient(Patient patient) {
        Patient existingPatient = getPatientById(patient.getId());
        if (existingPatient != null) {
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            return patientRepository.save(existingPatient);
        }
        return null;
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMedicationToPatient(Long patientId, Long medicationId) {
        Patient patient = getPatientById(patientId);
        Medication medication = medicationRepository.getReferenceById(medicationId);

        if (patient != null) {
            patient.getPrescribedMedications().add(medication);
            patientRepository.save(patient);
        }
    }
}
