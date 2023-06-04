package com.example.mindmeetapp.controller;

import com.example.mindmeetapp.model.PatientModel;
import com.example.mindmeetapp.service.PatientService;
import com.example.mindmeetapp.util.PatientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    @PostMapping ()
    public void createPatient(@RequestBody PatientModel patientModel ) throws Exception {

        service.createPatient(patientModel);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody PatientModel patientModel){
        patientModel.setId(id);
        service.updatePatient(patientModel);
    }

    @DeleteMapping ("/{id}")
    public void deletePatient(@PathVariable("id") Long id){
        service.deletePatient(id);

    }

    @GetMapping()
    public List<PatientModel> getPatients(){
        return service.getPatientsList();
    }
    @GetMapping ("/{id}")
    public PatientModel getTherapistById(@PathVariable("id")Long id){

        return service.getPatientById(id);
    }



}
