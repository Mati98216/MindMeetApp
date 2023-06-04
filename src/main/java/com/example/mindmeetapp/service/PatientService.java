package com.example.mindmeetapp.service;

import com.example.mindmeetapp.model.PatientModel;
import com.example.mindmeetapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientRepository repo;

    public void createPatient (PatientModel patient) {
        repo.save(patient);
    }

    public void updatePatient(PatientModel editPatient) {
        repo.save(editPatient);
    }
    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

    public PatientModel getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<PatientModel> getPatientsList() {
        return repo.findAll();
    }







}
