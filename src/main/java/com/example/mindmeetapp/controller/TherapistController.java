package com.example.mindmeetapp.controller;

import com.example.mindmeetapp.model.TherapistModel;
import com.example.mindmeetapp.service.TherapistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/therapists")
public class TherapistController {


    private final TherapistService service;

    @GetMapping(value = "")
    public List<TherapistModel> getTherapists(){
        return service.getTherapist();
    }
    @GetMapping ("/{id}")
    public TherapistModel getTherapistById(@PathVariable("id")Long id){

        return service.getTherapistById(id);

    }
    @PostMapping ("/")
    public void createTherapist(TherapistModel therapistModel ) throws Exception {

        service.createTherapist(therapistModel);
    }

    @PutMapping("/{id}")
    public void updateTherapist(TherapistModel therapistModel){
        service.updateTherapist(therapistModel);

    }

    @DeleteMapping ("/{id}")
    public void deleteTherapist(@PathVariable("id") Long id){
        service.deleteTherapist(id);

    }


}
